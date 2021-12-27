package com.kkb.hk.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kkb.hk.dao.HkBannerDao;
import com.kkb.hk.entity.HkBanner;
import com.kkb.hk.entity.page.PageResult;
import com.kkb.hk.service.HkBannerService;
import com.kkb.hk.utils.PageUtils;
import com.kkb.hk.utils.StringUtils;
import com.kkb.hk.vo.request.banner.HkBannerRequest;
import com.kkb.hk.vo.response.banner.HkBannerResponse;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @className HkBannerServiceImpl
 * @description:  banner表(HkBanner)表服务实现类
 * @author Allen
 * @date 2021/12/16 15:57
 */
@Service("hkBannerService")
public class HkBannerServiceImpl implements HkBannerService {
    @Resource
    private HkBannerDao hkBannerDao;


    private Jedis jedis = new Jedis("127.0.0.1",6379);

    /**
     * @description:  查询banner列表
     * @param: [hkBannerRequest]
     * @return: java.util.List<com.kkb.hk.vo.response.banner.HkBannerResponse>
     * @author Allen
     * @date: 2021/12/16 19:29
     */
    @Override
    public List<HkBannerResponse> qryList(HkBannerRequest hkBannerRequest) {
        //此处代码需要先从redis中获取，获取不到则取查数据库
        String key = hkBannerRequest.getTitle() +hkBannerRequest.getPageSize() + hkBannerRequest.getPageNum();
        if (key.equals("nullnullnull")){
            key = "all";
        }
        System.err.println(key);
        String historyJsON = jedis.get(key);

        if (StringUtils.isEmpty(historyJsON)) {
            //缓存中没有数据，查询数据库
            List<HkBannerResponse> list = hkBannerDao.qryList(hkBannerRequest);
            //此处代码需要把查出来的结果set redis缓存
            jedis.set(key,JSON.toJSONString(list));
            return list;
        }
        List<HkBannerResponse> list = JSON.parseObject(historyJsON, new TypeReference<List<HkBannerResponse>>() {});
        return list;
    }

    /**
     * @description:  查询banner列表分页查询
     * @param: [hkBannerRequest]
     * @return: com.kkb.hk.entity.page.PageResult
     * @author Allen
     * @date: 2021/12/16 17:48
     */
    @Override
    public PageResult qryListByPage(HkBannerRequest hkBannerRequest) {
        int pageNum = hkBannerRequest.getPageNum();
        int pageSize = hkBannerRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<HkBannerResponse> responseList = this.hkBannerDao.qryListByPage(hkBannerRequest);
        return PageUtils.getPageResult(new PageInfo<HkBannerResponse>(responseList));
    }


    /**
     * @description:  新增banner
     * @param: [hkBanner]
     * @return: int
     * @author xqy
     * @date: 2021/12/27 18:50
     */
    @Override
    public int saveBanner(HkBanner hkBanner) {
        hkBanner.setCreatedTime(new Date());
        return hkBannerDao.saveBanner(hkBanner);
    }

    /**
     * @description:  更新banner
     * @param: [hkBanner]
     * @return: int
     * @author xqy
     */
    @Override
    public int updateBanner(HkBanner hkBanner) {
        HkBanner oldBanner =  hkBannerDao.qryOneById(hkBanner.getBannerId());
        if(oldBanner!=null){
            hkBanner.setUpdatedTime(new Date());
            //修改人应该是根据实际的角色，我这里暂时固定
            hkBanner.setUpdatedBy("xqy");
            return hkBannerDao.updateBanner(hkBanner);
        }

        return 0;
    }

    /**
     * @description:  删除banner
     * @param: [bannerId]
     * @return: int
     * @author xqy
     */
    @Override
    public int deleteBanner(Integer bannerId) {
        HkBanner oldBanner =  hkBannerDao.qryOneById(bannerId);
        if(oldBanner!=null){
            return  hkBannerDao.deleteBanner(bannerId);
        }
        return 0;
    }
}
