package reinty.study.seckill.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reinty.study.seckill.core.common.CheckUtils;
import reinty.study.seckill.core.common.WebResult;
import reinty.study.seckill.core.common.exception.ExceptionCodeEnum;
import reinty.study.seckill.core.common.exception.SecKillBusinessException;
import reinty.study.seckill.core.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    public WebResult create(Long promoteId, Long goodsId, Long userId){
        try {
            CheckUtils.checkIfNull( promoteId, "促销Id为空", ExceptionCodeEnum.PARAM_ERROR);
            CheckUtils.checkIfNull( goodsId, "商品Id为空", ExceptionCodeEnum.PARAM_ERROR);
            CheckUtils.checkIfNull( userId, "用户Id为空", ExceptionCodeEnum.NOT_LOGIN);
            return orderService.createOrder(promoteId, goodsId, userId);
        }catch (SecKillBusinessException sbe){
            LOGGER.warn("下单接口逻辑处理失败，promoteId:{}, goodsId:{}, msg:{}", promoteId, goodsId, sbe.getMessage());
            return sbe.getExceptionServiceResponse();
        }catch (Exception ex){
            LOGGER.error("下单接口逻辑处理异常，promoteId:{}, goodsId:{}, ", promoteId, goodsId, ex);
            return WebResult.failResult();
        }
    }
}
