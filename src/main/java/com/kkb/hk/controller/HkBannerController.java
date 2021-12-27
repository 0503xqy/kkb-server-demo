package com.kkb.hk.controller;

import com.kkb.hk.entity.HkBanner;
import com.kkb.hk.service.HkBannerService;
import com.kkb.hk.utils.ReqResultUtil;
import com.kkb.hk.vo.request.banner.HkBannerRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className HkBannerController
 * @description:banner接口层
 * @author Allen
 * @date 2021/12/16 15:48
 */
@RestController
@RequestMapping("hkBanner")
@Slf4j
public class HkBannerController {


    @Resource
    private HkBannerService hkBannerService;

    /**
     * @description:  查询banner列表
     * @param: []
     * @return: org.springframework.http.ResponseEntity<java.lang.String>
     * @author Allen
     * @date: 2021/12/16 15:48
     */
    @RequestMapping(value = "/qryList", method = RequestMethod.POST)
    public ResponseEntity<String> qryList(HkBannerRequest hkBannerRequest) {
        log.info("进入banner列表接口");
        log.info("开始");
        return ReqResultUtil.genSuccessResultResponse(hkBannerService.qryList(hkBannerRequest));

    }

    /**
     * @description:分页查询banner列表
     * @param: [hkBannerRequest]
     * @return: org.springframework.http.ResponseEntity<java.lang.String>
     * @author Allen
     * @date: 2021/12/16 18:53
     */
    @RequestMapping(value = "/qryListByPage", method = RequestMethod.POST)
    public ResponseEntity<String> qryListByPage(HkBannerRequest hkBannerRequest) {
        log.info("进入banner列表接口");
        return ReqResultUtil.genSuccessResultResponse(hkBannerService.qryListByPage(hkBannerRequest));
    }


    /**
     * @description:新增banner
     * @param: [hkBanner]
     * @return: org.springframework.http.ResponseEntity<java.lang.String>
     * @author xqy
     * @date: 2021/12/27 18:45
     */
    @RequestMapping(value = "/saveBanner", method = RequestMethod.POST)
    public ResponseEntity<String> saveBanner(HkBanner hkBanner){
        log.info("新增banner");
        int flag = hkBannerService.saveBanner(hkBanner);
        if(flag==1){
            return ReqResultUtil.genSuccessResultResponse("添加成功");
        }
        return ReqResultUtil.genFailResultResponse("添加失败");
    }

    /**
     * @description:修改banner
     * @param: [hkBanner]
     * @return: org.springframework.http.ResponseEntity<java.lang.String>
     * @author xqy
     * @date: 2021/12/27 18:46
     */
    @RequestMapping(value = "/updateBanner", method = RequestMethod.POST)
    public ResponseEntity<String> updateBanner(HkBanner hkBanner){
        log.info("修改banner");
        int flag = hkBannerService.updateBanner(hkBanner);
        if (flag==1){
            return ReqResultUtil.genSuccessResultResponse("修改成功");
        }
        return ReqResultUtil.genFailResultResponse("修改失败");
    }

    /**
     * @description:删除banner
     * @param: [bannerId]
     * @return: org.springframework.http.ResponseEntity<java.lang.String>
     * @author xqy
     * @date: 2021/12/27 18:46
     */
    @RequestMapping(value = "/deleteBanner", method = RequestMethod.POST)
    public ResponseEntity<String> deleteBanner(Integer bannerId){
        log.info("删除banner");
        int flag = hkBannerService.deleteBanner(bannerId);
        if (flag==1){
            return ReqResultUtil.genSuccessResultResponse("删除成功");
        }
        return ReqResultUtil.genFailResultResponse("删除失败");
    }

}

