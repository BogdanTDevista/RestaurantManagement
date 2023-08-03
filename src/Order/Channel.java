package Order;

import java.util.Map;

public interface Channel {
    public void update(Map<Integer, Order> o);
}
