package vendingmachine.integration;

import vendingmachine.domain.Product;

public interface EmailService {
    void sendSupplyRequestToVendor(Product runningOutOf);
}
