package vendingmachine.domain

import spock.lang.Specification

class VendingMachineTest extends Specification {

    def "should display 'insert a coin' when ready"() {
        when:
        def vendingMachine = new VendingMachine()

        then:
        vendingMachine.display == "INSERT A COIN"
        vendingMachine.balance.value == 0
        vendingMachine.rejectedCoins() == []
    }

    def "should display how much money was inserted"() {
        given:
        def vendingMachine = new VendingMachine()

        when:
        coins.each({ vendingMachine.insert(it) })

        then:
        vendingMachine.display == displayedText
        vendingMachine.balance == new Money(balance)

        where:
        coins                       || balance | displayedText
        [Coin.DIME, Coin.NICKEL]    || 0.15    | "0.15"
        [Coin.QUARTER, Coin.NICKEL] || 0.30    | "0.30"
        [Coin.QUARTER, Coin.PENNY]  || 0.25    | "0.25"
    }

    def "should reject pennies"() {
        given:
        def vendingMachine = new VendingMachine()

        when:
        vendingMachine.insert(Coin.PENNY)

        then:
        vendingMachine.display == "INSERT A COIN"
        vendingMachine.rejectedCoins() == [Coin.PENNY]
        vendingMachine.getBalance() == Money.zero()
    }

    def "should allow to buy a product"() {
        given:
        def vendingMachine = new VendingMachine()
        4.times {
            vendingMachine.insert(Coin.QUARTER)
        }

        when:
        def result = vendingMachine.select(Product.COLA)

        then:
        result == PurchaseResult.DISPENSED
        vendingMachine.display == "THANK YOU"
        vendingMachine.display == "INSERT A COIN"
        vendingMachine.balance == Money.zero()
    }

    def "should return #expectedResult when a #product is selected after #coins are inserted"() {
        given:
        def vendingMachine = new VendingMachine()
        coins.each { vendingMachine.insert(it) }

        when:
        def purchaseResult = vendingMachine.select(product)

        then:
        purchaseResult == expectedResult

        where:
        coins                                                    | product       || expectedResult
        [Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.QUARTER] | Product.COLA  || PurchaseResult.DISPENSED
        [Coin.QUARTER, Coin.QUARTER, Coin.QUARTER]               | Product.COLA  || PurchaseResult.REJECTED
        [Coin.QUARTER, Coin.QUARTER]                             | Product.CHIPS || PurchaseResult.DISPENSED
    }

    def "Should show the price of the product when the balance is insufficient"() {
        given:
        def vendingMachine = new VendingMachine()
        insertedCoins.each({ vendingMachine.insert(it) })

        when:
        vendingMachine.select(product)

        then:
        vendingMachine.display == firstShownText
        vendingMachine.display == secondShownText

        vendingMachine.balance == balance


        where:
        insertedCoins  | product       || firstShownText | secondShownText | balance
        []             | Product.COLA  || "PRICE 1.00"   | "INSERT A COIN" | Money.zero()
        [Coin.QUARTER] | Product.CANDY || "PRICE 0.65"   | "0.25"          | new Money(0.25)
    }

}