package com.newtouch.glzdept.buss.service.impl;

import com.newtouch.common.entity.base.MessageType;
import com.newtouch.common.entity.base.Page;
import com.newtouch.common.exception.BlcException;
import com.newtouch.glzdept.buss.dao.PovertyVisitDao;
import com.newtouch.glzdept.buss.entity.VO.PovertyVisitVO;
import com.newtouch.glzdept.buss.service.IPovertyVisitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service
public class PovertyVisitServiceImpl implements IPovertyVisitService {

    @Resource
    PovertyVisitDao povertyVisitDao;

    @Override
    public void saveVisitInfo(PovertyVisitVO visitVO) {
        if(visitVO == null){
            return ;
        }
        //TODO 缺少当前用户id赋值
        visitVO.setIsdelete("n");
        visitVO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        Integer key = povertyVisitDao.insert(visitVO);
        if(key == null){
            throw new BlcException(MessageType.ERROR);
        }
    }

    @Override
    public Page<PovertyVisitVO> selectPovertyVisitPage(PovertyVisitVO povertyVisitVO, Page page) {
        page.init();
        page.setTotalNum(povertyVisitDao.total(povertyVisitVO));
        page.setList(povertyVisitDao.selectPovertyVisitPage(povertyVisitVO,page));
        return page;
    }

    @Override
    public PovertyVisitVO visitInfo(PovertyVisitVO povertyVisitVO) {
        return povertyVisitDao.visitInfo(povertyVisitVO);
    }

    @Override
    public PovertyVisitVO visitInfoImgs(PovertyVisitVO povertyVisitVO) {
        return povertyVisitDao.visitInfoImgs(povertyVisitVO);
    }

    @Override
    public void updateById(PovertyVisitVO povertyVisitVO) {
        povertyVisitDao.updateById(povertyVisitVO);
    }

}
