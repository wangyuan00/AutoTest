package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {
    @Test(dependsOnGroups = "loginTrue", description = "获取userId=1的用户信息")
    public void getUserInfoTest() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlsession();
        GetUserInfoCase getUserInfoCase = session.selectOne("getUserInfoCase", 1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);

//        发送请求，获取结果
        JSONArray resultJson = getJsonResult(getUserInfoCase);
//        验证返回结果
        //Thread.sleep(3000);
        User user = session.selectOne(getUserInfoCase.getExpected(), getUserInfoCase);
        List userlist = new ArrayList();
//        将查询出来的user放到userlist中，只有这样才能转换为JSONArray
        userlist.add(user);
        JSONArray jsonArray = new JSONArray(userlist);
        Assert.assertEquals(jsonArray.toString(), resultJson.toString());
    }

    private JSONArray getJsonResult(GetUserInfoCase getUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param = new JSONObject();
//        这里传入的参数要跟接口需要的参数保持一致
        param.put("id", getUserInfoCase.getUserId());
//        param.put("expected",getUserInfoCase.getExpected());
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


        //List resultList = Arrays.asList(result);
        JSONArray array = new JSONArray(result);
        return array;
    }
}
