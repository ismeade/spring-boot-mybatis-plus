package com.ade.exp.springbootmybatisplus.mapper;

import com.ade.exp.springbootmybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void simpleThread() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (long i = 0; i < 1_000_000; i++) {
            User user = new User();
            user.setName("aaaaa");
            user.setEmail("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            user.setId(i);
            userMapper.insert(user);
        }
        stopWatch.stop();
        System.out.printf("用时: %d ms\n", stopWatch.getTotalTimeMillis());
        System.out.printf("写入数据 %d 条\n", userMapper.selectCount(null));
    }

    @Test
    public void multiThread() throws InterruptedException {
        Executor executor = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(1_000_000);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (long i = 0; i < 1_000_000; i++) {
            long finalI = i;
            executor.execute(() -> {
                User user = new User();
                user.setName("Jack");
                user.setEmail("jack@test.com");
                user.setId(finalI);
                userMapper.insert(user);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        stopWatch.stop();
        System.out.printf("用时: %d ms\n", stopWatch.getTotalTimeMillis());
        System.out.printf("写入数据 %d 条\n", userMapper.selectCount(null));
    }

}