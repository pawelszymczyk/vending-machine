package vendingmachine.domain;

/**
 *
 * @author wioleta.gozdzik
 */
public interface CoinBank {

    void returnCoin(Coin coin);
    
    void returnChange(Money amount);
}
