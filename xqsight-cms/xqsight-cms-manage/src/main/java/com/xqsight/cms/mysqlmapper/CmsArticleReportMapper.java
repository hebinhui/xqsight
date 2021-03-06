/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 
package com.xqsight.cms.mysqlmapper;

import java.util.List;

import com.xqsight.cms.model.vo.CmsArticleReportVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xqsight.cms.model.CmsArticleReport;

/**
 * <p>数据库Mapper类</p>
 * <p></p>
 * @since 2016-09-08 08:42:11
 */
public interface CmsArticleReportMapper {

	@Insert(" INSERT INTO CMS_ARTICLE_REPORT(ASSOCICATION_ID,REPORT_TYPE,REPORT_CONTENT,DEAL_STATUS,ACTIVE,CREATE_TIME,CREATE_OPR_ID,REMARK)VALUES(#{associcationId,jdbcType=NUMERIC},#{reportType,jdbcType=NUMERIC},#{reportContent,jdbcType=VARCHAR},#{dealStatus,jdbcType=NUMERIC},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	void saveCmsArticleReport(CmsArticleReport cmsArticleReport);
	
	@Update(" UPDATE CMS_ARTICLE_REPORT SET DEAL_STATUS=0,UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP},UPD_OPR_ID=#{updOprId,jdbcType=VARCHAR},REMARK=#{remark,jdbcType=VARCHAR} WHERE REPORT_ID=#{reportId,jdbcType=NUMERIC}")
	void updateCmsArticleReport(CmsArticleReport cmsArticleReport);
	
	@Delete(" DELETE FROM CMS_ARTICLE_REPORT WHERE REPORT_ID=#{reportId,jdbcType=NUMERIC}")
	void deleteCmsArticleReport(@Param("reportId") Long reportId);
	
	@Select(" SELECT REPORT_ID,REPORT_CONTENT,ASSOCICATION_ID,REPORT_TYPE,DEAL_STATUS,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_ARTICLE_REPORT WHERE REPORT_ID=#{reportId,jdbcType=NUMERIC}")
	CmsArticleReport queryCmsArticleReportById(@Param("reportId") Long reportId);

	@Select(" SELECT CAR.REPORT_ID,CAR.REPORT_CONTENT,CAR.ASSOCICATION_ID,CAR.DEAL_STATUS,CAR.CREATE_TIME,CAR.REMARK,CR.ARTICLE_TITLE,CR.ARTICLE_ID FROM CMS_ARTICLE_REPORT CAR LEFT JOIN CMS_ARTICLE CR ON CAR.ASSOCICATION_ID = CR.ARTICLE_ID  WHERE CAR.REPORT_CONTENT LIKE '%${reportContent}%' ORDER BY CAR.CREATE_TIME DESC")
	List<CmsArticleReportVo> queryCmsArticleReport(@Param("reportContent") String reportContent);
}