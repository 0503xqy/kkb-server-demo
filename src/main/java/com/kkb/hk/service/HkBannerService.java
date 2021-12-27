package com.kkb.hk.service;

import com.kkb.hk.entity.HkBanner;
import com.kkb.hk.entity.page.PageResult;
import com.kkb.hk.vo.request.banner.HkBannerRequest;
import com.kkb.hk.vo.response.banner.HkBannerResponse;

import java.util.List;

/**
 * @className HkBannerService
 * @description:  表(HkBanner)表服务接口
 * @author Allen
 * @date 2021/12/16 15:56
 */
public interface HkBannerService {

    /**
     * @description:  查询banner列表
     * @param: [hkBannerRequest]
     * @return: java.util.List<com.kkb.hk.vo.response.banner.HkBannerResponse>
     * @author Allen
     * @date: 2021/12/16 16:23
     */
    List<HkBannerResponse> qryList(HkBannerRequest hkBannerRequest);

    /**
     * @description:  查询banner列表分页查询
     * @param: [hkBannerRequest]
     * @return: com.kkb.hk.entity.page.PageResult
     * @author Allen
     * @date: 2021/12/16 16:23
     */
    PageResult qryListByPage(HkBannerRequest hkBannerRequest);

    /**
     * @description:  新增banner
     * @param: [hkBanner]
     * @return: com.kkb.hk.entity.page.PageResult
     * @author xqy
     */
    int saveBanner(HkBanner hkBanner);

    /**
     * @description:  新增banner
     * @param: [hkBanner]
     * @return: int
     * @author xqy
     */
    int updateBanner(HkBanner hkBanner);

    /**
     * @description:  删除banner
     * @param: [bannerId]
     * @return: int
     * @author xqy
     */
    int deleteBanner(Integer bannerId);

}
