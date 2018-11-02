package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
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
import java.util.Arrays;
import java.util.List;

public class GetUserInfoListTest {
    @Test(dependsOnGroups = "loginTrue", description = "获取用户信息列表接口测试")
    public void getUserInfoList() throws IOException {
        SqlSession session = DatabaseUtil.getSqlsession();
        GetUserListCase getUserInfoListCase = session.selectOne("getUserListCase", 1);
        System.out.println("获取用户信息列表："+getUserInfoListCase.toString());
        System.out.println("请求的URL："+TestConfig.getUserListUrl);
//        发送请求，获取结果
        JSONArray resultJson = getJsonResult(getUserInfoListCase);
//        验证结果
        List<User> userList = session.selectList(getUserInfoListCase.getExpected(), getUserInfoListCase);
        for (User u : userList) {
            System.out.println("获取的user" + u.toString());
        }
        JSONArray userListJson = new JSONArray(userList);
        Assert.assertEquals(userListJson.length(), resultJson.length());//长度一样
        for (int i = 0; i < resultJson.length(); i++) {
            JSONObject expected = (JSONObject) resultJson.get(i);
            JSONObject actual = (JSONObject) userListJson.get(i);
            Assert.assertEquals(expected, actual);//且内容一样
        }

    }

    private JSONArray getJsonResult(GetUserListCase getUserInfoListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("userName", getUserInfoListCase.getUserName());
        param.put("age", getUserInfoListCase.getAge());
        param.put("sex", getUserInfoListCase.getSex());
//        param.put("expected", getUserInfoListCase.getExpected());
//        设置请求头
        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
//        设置cookie
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
//        将result 由String转为JSONArray
//        List resultList = Arrays.asList(result);
        JSONArray jsonArray = new JSONArray(result);
        return jsonArray;
    }
}
