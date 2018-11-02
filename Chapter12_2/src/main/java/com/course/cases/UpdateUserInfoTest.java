package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.UpdateUserInfoCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserInfoTest {
    @Test(dependsOnGroups = "loginTrue", description = "更新用户信息")
    public void updateUserInfo() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlsession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase", 1);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);

//        发送请求，获取结果
        int result = getResult(updateUserInfoCase);
//        验证返回结果
        Thread.sleep(3000);
        User user = session.selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }


    @Test(dependsOnGroups = "loginTrue", description = "删除用户信息")
    public void deleteUser() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlsession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase", 2);
        System.out.println(updateUserInfoCase.toString());
        System.out.println(TestConfig.updateUserInfoUrl);
//        发送请求，获取结果
        int result = getResult(updateUserInfoCase);
//        验证返回结果
        Thread.sleep(3000);
        User user = session.selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }

    private int getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {
        HttpPost post=new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id", updateUserInfoCase.getUserId());
        param.put("userName", updateUserInfoCase.getUserName());
        param.put("sex", updateUserInfoCase.getSex());
        param.put("age", updateUserInfoCase.getAge());
        param.put("permission", updateUserInfoCase.getPermission());
        param.put("isDelete", updateUserInfoCase.getIsDelete());
        param.put("expected", updateUserInfoCase.getExpected());
        //        设置头信息
        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        //        设置cookie
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result;
        //        执行
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        return Integer.parseInt(result);
    }
}
