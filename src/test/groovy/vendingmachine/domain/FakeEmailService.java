package vendingmachine.domain;

import vendingmachine.integration.EmailService;

/**
 *
 * @author wioleta.gozdzik
 */
public class FakeEmailService implements EmailService{

    @Override
    public void sendSupplyRequestToVendor(Product runningOutOf) {
    }

}
