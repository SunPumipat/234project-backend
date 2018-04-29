package camt.se234.project.service;

import camt.se234.project.dao.OrderDao;
import camt.se234.project.entity.SaleOrder;
import camt.se234.project.entity.SaleTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class SaleOrderServiceImpl implements SaleOrderService {
    OrderDao orderDao;
    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public SaleOrder addSaleOrder(SaleOrder order) {
        SaleOrder saleOrder = orderDao.addOrder(order);
        for (SaleTransaction transaction : saleOrder.getTransactions()) {
            transaction.setOrder(saleOrder);

        }
        return saleOrder;
    }

    @Override
    public List<SaleOrder> getSaleOrders() {
        return orderDao.getOrders();
    }

    @Override
    public double getAverageSaleOrderPrice() {
        List<SaleOrder> orders = orderDao.getOrders();
        double totalPrice = 0;
        for (SaleOrder order : orders) {
            totalPrice += order.getTotalPrice();
        }
        return totalPrice/orders.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaleOrderServiceImpl)) return false;
        SaleOrderServiceImpl that = (SaleOrderServiceImpl) o;
        return Objects.equals(orderDao, that.orderDao);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderDao);
    }

}
