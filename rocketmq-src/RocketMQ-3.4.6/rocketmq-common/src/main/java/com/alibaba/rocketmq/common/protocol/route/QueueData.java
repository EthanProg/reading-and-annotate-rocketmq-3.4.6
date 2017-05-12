/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * $Id: QueueData.java 1835 2013-05-16 02:00:50Z shijia.wxr $
 */
package com.alibaba.rocketmq.common.protocol.route;

/**
 * 这个QueueData类是topic在某一个brokername下面的队列元数据
 * ， 其中包含了brokername(一主多备组成的broker单元） ， 读写队列的数量，队列的读写权限
 */
public class QueueData implements Comparable<QueueData> {
    private String brokerName;
    /*
    * consumequeue/topic/目录下的数字编号，这些编号由写队列数决定。
例如创建队列的时候设置读队列数为2，写队列为10，那么消息的索引信息会记录到consumequeue/topic/下面的0-9数字编号文件中，
但是因为读队列数为2，那么也就只能消费0和1索引队列指定的消息可以被消费者消费，索引2-9队列上的消息就不能消费
    * */
    private int readQueueNums;
    //commitlog中存储的消息格式已经指定好该消息对应的topic，已经存到consumeQueue中对应的topic的那个队列，究竟写入那个consumequeue的那个queueid，这是由客户端投递消息的时候
    //组包commitlog格式消息的时候指定的，客户端做的负载均衡，选择不同queueid投递,
    //客户端投递的时候选择的是从topic对应的writeQueue中选择一个queue投递，而不是选择的readqueue
    private int writeQueueNums;
    private int perm;
    private int topicSynFlag;


    public String getBrokerName() {
        return brokerName;
    }


    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }


    public int getReadQueueNums() {
        return readQueueNums;
    }


    public void setReadQueueNums(int readQueueNums) {
        this.readQueueNums = readQueueNums;
    }


    public int getWriteQueueNums() {
        return writeQueueNums;
    }


    public void setWriteQueueNums(int writeQueueNums) {
        this.writeQueueNums = writeQueueNums;
    }


    public int getPerm() {
        return perm;
    }


    public void setPerm(int perm) {
        this.perm = perm;
    }


    public int getTopicSynFlag() {
        return topicSynFlag;
    }


    public void setTopicSynFlag(int topicSynFlag) {
        this.topicSynFlag = topicSynFlag;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((brokerName == null) ? 0 : brokerName.hashCode());
        result = prime * result + perm;
        result = prime * result + readQueueNums;
        result = prime * result + writeQueueNums;
        result = prime * result + topicSynFlag;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QueueData other = (QueueData) obj;
        if (brokerName == null) {
            if (other.brokerName != null)
                return false;
        }
        else if (!brokerName.equals(other.brokerName))
            return false;
        if (perm != other.perm)
            return false;
        if (readQueueNums != other.readQueueNums)
            return false;
        if (writeQueueNums != other.writeQueueNums)
            return false;
        if (topicSynFlag != other.topicSynFlag)
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "QueueData [brokerName=" + brokerName + ", readQueueNums=" + readQueueNums
                + ", writeQueueNums=" + writeQueueNums + ", perm=" + perm + ", topicSynFlag=" + topicSynFlag
                + "]";
    }


    @Override
    public int compareTo(QueueData o) {
        return this.brokerName.compareTo(o.getBrokerName());
    }
}
