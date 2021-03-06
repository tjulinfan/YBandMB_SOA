/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.potatoni.orderws;

import com.potatoni.entity.Bid;
import com.potatoni.entity.OrderForm;
import com.potatoni.exception.InternalException;
import com.potatoni.exception.InvalidSessionException;
import com.potatoni.exception.UserNotLoggedInException;
import com.potatoni.helper.BidHelper;
import com.potatoni.helper.OrderHelper;
import com.potatoni.helper.SessionHelper;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.ClientErrorException;

/**
 *
 * @author LinFan
 */
@WebService(serviceName = "OrderWebService")
public class OrderWebService {

    /**
     * 生成订单
     */
    @WebMethod(operationName = "genOrder")
    public void genOrder(@WebParam(name= "bidId")Integer bidId, @WebParam(name = "sessionId") String sessionId) throws InternalException, UserNotLoggedInException {
        try {
            Bid bid = BidHelper.getBid(bidId);
            OrderForm newOrder = new OrderForm();
            newOrder.setBookId(bid.getBookId());
            newOrder.setCustomerId(bid.getUserId());
            newOrder.setPrice(bid.getPrice());
            newOrder.setSoldDate(new Date());
            newOrder.setSellerId(SessionHelper.getUserId(sessionId));
            OrderHelper.genOrder(newOrder);
        } catch (InvalidSessionException ex) {
            throw new UserNotLoggedInException();
        } catch (ClientErrorException ex) {
            throw new InternalException();
        }
    }
}
