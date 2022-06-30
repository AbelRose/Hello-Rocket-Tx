//package com.matrix.txproducer.listener;
//
//import com.alibaba.fastjson.JSON;
//import com.matrix.txproducer.entity.OrderBase;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.producer.LocalTransactionState;
//import org.apache.rocketmq.client.producer.TransactionListener;
//import org.apache.rocketmq.common.message.Message;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.springframework.stereotype.Component;
//
///**
// * @author yihaosun
// * @date 2022/6/30 21:46
// */
//@Component
//@Slf4j
//public class OrderTransactionListener implements TransactionListener {
//    private final OrderService orderService;
//
//    private final TransactionLogDao transactionLogDao;
//
//    public OrderTransactionListener(OrderService orderService, TransactionLogDao transactionLogDao) {
//        this.orderService = orderService;
//        this.transactionLogDao = transactionLogDao;
//    }
//
//    /**
//     * 发送half msg 返回send ok后调用的方法
//     *
//     * 注意不在这个地方加 Transaction 而是在orderService方法里面加上 Transaction
//     *
//     * @param message message
//     * @param o o
//     * @return LocalTransactionState
//     */
//    @Override
//    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
//        log.info("开始执行本地事务....");
//        LocalTransactionState state;
//        try{
//            String body = new String(message.getBody());
//            OrderBase order = JSON.parseObject(body, OrderBase.class);
//            orderService.createOrder(order,message.getTransactionId());
//            // 返回commit后，消息能被消费者消费
//            state = LocalTransactionState.COMMIT_MESSAGE;
////            state = LocalTransactionState.ROLLBACK_MESSAGE;
////            state = LocalTransactionState.UNKNOW;
////            TimeUnit.MINUTES.sleep(1);
//            log.info("本地事务已提交。{}",message.getTransactionId());
//
//
//        }catch (Exception e){
//            log.info("执行本地事务失败。{}",e);
//            state = LocalTransactionState.ROLLBACK_MESSAGE;
//        }
//        return state;
//    }
//
//    /**
//     * 回查 走的方法
//     * @param messageExt messageExt
//     * @return LocalTransactionState
//     */
//    @Override
//    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
//        // 可以设置回查次数 回查多次失败 人工补偿。提醒人。发邮件的。
//        log.info("开始回查本地事务状态。{}",messageExt.getTransactionId());
//        LocalTransactionState state;
//        String transactionId = messageExt.getTransactionId();
//        // 看看事务表是否存在这条记录 如果有的话 就说明已经commit了
//        if (transactionLogDao.selectCount(transactionId)>0){
//            state = LocalTransactionState.COMMIT_MESSAGE;
//        }else {
//            // 第一步的时候是UNKNOWN 过一会还会再回查一次 如果是ROLLBACK就是直接不会差了
//            state = LocalTransactionState.UNKNOW;
//        }
//        log.info("结束本地事务状态查询：{}",state);
//        return state;
//    }
//}
