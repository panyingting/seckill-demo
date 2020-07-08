package reinty.study.seckill.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reinty.study.seckill.core.common.WebResult;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @RequestMapping("/detail")
    public WebResult getGoodsDetail(){

    }
}
