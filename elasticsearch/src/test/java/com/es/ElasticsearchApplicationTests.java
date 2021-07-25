package com.es;

import com.alibaba.fastjson.JSON;
import com.es.entity.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class ElasticsearchApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    void contextLoads() {
    }

    @Test
    void createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("test1");
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    @Test
    void existIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("test1");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("test1");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    @Test
    void addDocument() throws IOException {
        User user = new User(1, "tallon");

        IndexRequest request = new IndexRequest("test1")
                .id("1")
                .timeout(TimeValue.timeValueSeconds(1))
                .timeout("1s")
                .source(JSON.toJSONString(user), XContentType.JSON);

        IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(index.toString());
    }

    @Test
    void getDocument() throws IOException {
        GetRequest request = new GetRequest("test1", "1");
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
        System.out.println(response);
    }

    @Test
    void updateDocument() throws IOException {
        User user = new User(1, "eric");
        // fixme 旧版本 es 必须指定 type ，待改进
        UpdateRequest request = new UpdateRequest("test1", "1")
                .timeout("1s")
                .doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    @Test
    void deleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest("test1", "1").timeout("1s");
        DeleteResponse delete = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(delete);
    }

    /**
     * 批量插入
     */
    @Test
    void bulkRequest() throws IOException {
        // fixme 待完善
        List<User> userList = List.of(User.builder().id(2).name("e").build(), User.builder().id(3).name("d").build());
        BulkRequest request = new BulkRequest().timeout("3s");
        userList.forEach(d -> request.add(new IndexRequest("test1").source(JSON.toJSONString(d), XContentType.JSON)));
        BulkResponse response = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.hasFailures());

    }
}
