package vendingmachine.domain

import spock.lang.Specification

class DisplayTest extends Specification {

    def "should show text"() {
        given:
        def display = new Display()

        when:
        display.show("foo")

        then:
        display.text == "foo"
    }

    def "should show text for a moment"() {
        given:
        def display = new Display()
        display.show("foo")

        when:
        display.showForAMoment("bar")

        then:
        display.text == "bar"
        display.text == "foo"
    }



}
