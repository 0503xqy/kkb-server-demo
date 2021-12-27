package com.kkb.hk.dao;

import com.kkb.hk.entity.HkBanner;
import com.kkb.hk.vo.request.banner.HkBannerRequest;
import com.kkb.hk.vo.response.banner.HkBannerResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className HkBannerDao
 * @description:  表(HkBanner)表数据库访问层
 * @author Allen
 * @date 2021/12/16 15:49
 */
@Mapper
public interface HkBannerDao {

    /**
     * @description:查询banner列表
     * @param: [hkBannerRequest]
     * @return: java.util.List<com.kkb.hk.vo.response.banner.HkBannerResponse>
     * @author Allen
     * @date: 2021/12/16 16:25
     */
    List<HkBannerResponse> qryList(HkBannerRequest hkBannerRequest);

    /**
     * @description:查询banner列表分页查询
     * @param: [hkBannerRequest]
     * @return: java.util.List<com.kkb.hk.vo.response.banner.HkBannerResponse>
     * @author Allen
     * @date: 2021/12/16 16:27
     */
    List<HkBannerResponse> qryListByPage(HkBannerRequest hkBannerRequest);

//    需要实现 Banner 表的增（add），删（del），改（edit），
//    查（qry）的接口

    /**
     * @description:新增banner
     * @param: [hkBanner]
     * @return: int
     * @author xqy
     * @date: 2021/12/27 18:42
     */
    int saveBanner(HkBanner hkBanner);

    /**
     * @description:删除banner
     * @param: [bannerId]
     * @return: int
     * @author xqy
     * @date: 2021/12/27 18:43
     */
    int deleteBanner(@Param("bannerId")Integer bannerId);

    /**
     * @description:修改banner
     * @param: [hkBanner]
     * @return: int
     * @author xqy
     * @date: 2021/12/27 18:44
     */
    int updateBanner(HkBanner hkBanner);

    /**
     * @description:根据id查询banner
     * @param: [HkBanner]
     * @return: int
     * @author xqy
     * @date: 2021/12/27 18:44
     */
    HkBanner qryOneById(@Param("bannerId") Integer bannerId);

}

