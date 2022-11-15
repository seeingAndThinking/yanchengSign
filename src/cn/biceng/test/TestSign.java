package cn.biceng.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import com.eseals.pdf.GMStamper;
import com.eseals.pdf.GMStamperWithPFX;
import com.eseals.pdf.StampVerifyResult;
import com.eseals.pdf.StampVerifyResult.Certificate;
import com.eseals.util.Base64Util;

import cn.biceng.pojo.StampPosition;
import cn.biceng.service.IBicengGMSealService;
import cn.biceng.service.IBicengUserService;
import cn.biceng.service.impl.BiengGMSealServiceImpl;
import cn.biceng.service.impl.BiengUserServiceImpl;
import cn.biceng.util.FileUtil;
import cn.biceng.util.ParmUtil;
import cn.biceng.util.SealLogUtil;
import cn.eseals.seal.data.SealInfo;

public class TestSign {
//	byte[] pdfData = FileUtil.File2byte(new File("d://TestData/testBinhai1.pdf"));
	byte[] pdfData = FileUtil.File2byte(new File("E://下载.pdf"));
	IBicengUserService userService = new BiengUserServiceImpl();
	IBicengGMSealService gmsealservice = new BiengGMSealServiceImpl();
	private String sealId = "91610103710162506G";//取章id
	private String sealplatUrl = "http://222.188.107.162:8090";//平台地址
	private String certId = "77A60000002B8F159C209375";//签名服务器容器id
	
	@Test
	public void testStampWithPostion() throws Exception{
		//位置盖章
		String re = userService.getSealByMobile(sealId,sealplatUrl,false,"","");
		System.out.println(re);
		SealInfo info = ParmUtil.dealResSeal(re, "");
		byte[] end = gmsealservice.pdfStampWithPostion(info, certId, pdfData, new StampPosition(1, 0, 0));
		FileUtil.byte2File(end, "d://", "test1.pdf");
		
		//验证
		List<StampVerifyResult> gmVerify = GMStamper.verify(end, "", "");
		for (StampVerifyResult stampVerifyResult : gmVerify) {
			Certificate certificate = stampVerifyResult.getCertificate();
			System.out.println(certificate);
			System.out.println("用户证书CN项"+certificate.getSubject());
			boolean success = stampVerifyResult.isSuccess();
			System.out.println("签名验证结果"+success);
			if(!success){
				throw new RuntimeException("pdf验证失败");
			}
		}
		//盖章日志
//		SimpleDateFormat sdf  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ); 
//		String setUsingLog = SealLogUtil.setUsingLog("gym111","用戶名:ycl", info.getSealId()+"", "127.0.0.1:8888", sdf.format(new Date()), "盖章", sealplatUrl);
//		System.out.println(setUsingLog);

	}
	
	@Test
	public void test() throws Exception{
		System.out.println(1);
		byte[] pdfData = FileUtil.File2byte(new File("D:\\991detech.pdf"));

		List<StampVerifyResult> verify = GMStamper.verify(pdfData, "", "");
		for (StampVerifyResult stampVerifyResult : verify) {
			System.out.println(stampVerifyResult);
		}
	}
	//关键字盖章
	@Test
	public void testStampWithKeyWord() throws Exception{
		String re = userService.getSealByMobile(sealId, sealplatUrl,false,"","");
		SealInfo info = ParmUtil.dealResSeal(re, "");
		String keyword = "射阳县不动产登记中心";//文档中需要存在该关键字
		byte[] pdfStampWithKeyWordBox = gmsealservice.pdfStampWithKeyWordBox(info, certId,pdfData, keyword, 0, 0, 0);
		FileUtil.byte2File(pdfStampWithKeyWordBox, "e://", "下载.pdf");
		
	}
	//骑缝盖章
	@Test
	public void testStampWithQF() throws Exception{
		String re = userService.getSealByMobile(sealId,sealplatUrl,false,"","");
		//解析印章数据
		SealInfo info = ParmUtil.dealResSeal(re, "");
		byte[] pdfStampQFZ = gmsealservice.pdfStampQFZ(info, certId, pdfData, 1, 5, 200, false);
		FileUtil.byte2File(pdfStampQFZ, "d://", "testetsststst.pdf");
		
	}
	
	
	@Test
	public void testGetSeal() throws Exception{
		String re = userService.getSealByMobile(sealId,sealplatUrl,false,"","");
		System.out.println(re);
	}
	
	@Test
	public void testPersionalSignature() throws Exception{
//		byte[] file2byte = FileUtil.File2byte(new File("d://TestData//3.bmp"));
//		byte[] pdfData = FileUtil.File2byte(new File("d://TestData/test.pdf"));
//
//		byte[] pdfStampWithPersonalSignature = gmsealservice.pdfStampWithPersonalSignatureTest(Base64Util.encode(file2byte),100,50, "杨晨", "17634249117", pdfData,  new StampPosition(1, 0, 0),
//				"http://180.96.13.210:8802/cert/issueCert",false,"","");
//		FileUtil.byte2File(pdfStampWithPersonalSignature, "d://", "guoxin.pdf");
//		List<StampVerifyResult> verify = GMStamperWithPFX.verify(pdfStampWithPersonalSignature, "", "");
//		for (StampVerifyResult stampVerifyResult : verify) {
//			System.out.println(stampVerifyResult.isSuccess());// 验证结果
//			System.out.println(stampVerifyResult.getCertificate());// 签章证书信息
//		}
	}
}
