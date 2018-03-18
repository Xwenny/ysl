package com.wenny.ysl.portal.service;

import com.wenny.ysl.domain.TaotaoResult;
import com.wenny.ysl.domain.TbItem;
import com.wenny.ysl.portal.pojo.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utils.CookieUtils;
import utils.HttpClientUtil;
import utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ItemServiceImpl itemService;

    @Override
    public TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
        CartItem cartItem = null;
        List<CartItem> itemList = getCartItemList(request);
        for (CartItem cItem : itemList) {
            if (cItem.getId() == itemId) {
                cItem.setNum(cItem.getNum() + num);
                cartItem = cItem;
                break;
            }
        }
        if (cartItem == null) {
            cartItem = new CartItem();


            String json = HttpClientUtil.doGet(itemService.REST_BASE_URL + itemService.ITEM_INFO_URL + itemId);
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItem.class);
            if (taotaoResult.getStatus() == 200) {
                TbItem item = (TbItem) taotaoResult.getData();
                cartItem.setId(item.getId());
                cartItem.setNum(num);
                cartItem.setTitle(item.getTitle());
                cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
                cartItem.setPrice(item.getPrice());
            }
            itemList.add(cartItem);
        }
        CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
        return TaotaoResult.ok();
    }

    @Override
    public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> cartItemList = getCartItemList(request);
        return cartItemList;
    }

    @Override
    public TaotaoResult deleteCartItem(long itemId,HttpServletRequest request,HttpServletResponse response) {
        List<CartItem> itemList = getCartItemList(request);
        for (CartItem cartItem:itemList){
            itemList.remove(cartItem);
            break;
        }
        CookieUtils.setCookie(request,response,"TT_CART",JsonUtils.objectToJson(itemList),true);
        return TaotaoResult.ok();
    }

    private List<CartItem> getCartItemList(HttpServletRequest request) {
        String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
        if (cartJson == null) {
            return new ArrayList<>();
        }
        try {


            List<CartItem> list = JsonUtils.jsonToList(cartJson, CartItem.class);

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();

    }
}
