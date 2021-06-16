package bugtest;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import javax.naming.Reference;


/**
 * fastjson bug复现
 *
 * @author: huangtao3
 * @since: 2021/5/11 13:54
 */
public class FastJsonBug {
    @Test
    public void test() {
        Reference ref= new Reference("","","");
        String source = "{\n" +
                "        \"@type\":\"bugtest.Entity\",\n" +
                "        \"name\":\"黄涛\",\n" +
                "        \"age\":\"23\"\n" +
                "    }";
        Object parse = JSONObject.parse(source);
        System.out.println(parse);
    }
}
