package vendingmachine.domain;

class Order {

    private Product product;
    private Money amountLeftToPay;

    Order(Product product, Money amountLeftToPay) {
        this.product = product;
        this.amountLeftToPay = amountLeftToPay;
    }

    boolean isOrderComplete() {
        return this.amountLeftToPay.isZero();
    }

    void adjustPaymnet(Money amountPayed) {
        this.amountLeftToPay = amountLeftToPay.substract(amountPayed);
    }

}
