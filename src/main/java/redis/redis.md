#Redis安装及基本使用

##redis特点

1.Redis支持数据的持久化，可以将内存中的数据保存在磁盘中，重启的时候可以再次加载进行使用。

2.Redis不仅仅支持简单的key-value类型的数据，同时还提供list，set，sorted-set，hash等数据结构的存储。

3.Redis支持数据的备份，即master-slave模式的数据备份。

##redis安装(windows)

下载地址：https://github.com/MSOpenTech/redis/releases。

Redis支持32位和64位。这个需要根据你系统平台的实际情况选择

安装完成后，打开一个cmd窗口，使用cd命令切换目录到redis安装目录，运行redis-server.exe redis.windows.conf 。

如果想方便的话，可以把redis的路径加到系统的环境变量里，这样就省得再输路径了.

有一些博客说后面的那个redis.windows.conf 可以省略，如果省略，会启用默认的。但是据我自己使用情况来看，并不能忽略，并且只输入redis.windows.conf还不够，会报错“Invalid argument during startup: Failed to open the .conf file: redis.windows.conf CWD=C:\Users\xjwhh”，解决方法是将这个配置文件的绝对路径写上去

服务器打开后，cmd窗口如下：

这是需要再开一个cmd窗口作为客户端，同时原来的窗口作为服务器端，不能关闭

切换到客户端窗口，进入安装目录，运行 redis-cli.exe -h 127.0.0.1 -p 6379 ，就连接上了

##数据类型及命令行操作

###Redis键(key)

基本语法：redis 127.0.0.1:6379> COMMAND KEY_NAME

###Redis字符串(String)

Redis字符串数据类型的相关命令用于管理redis字符串值

基本语法：redis 127.0.0.1:6379> COMMAND KEY_NAME

###Redis哈希(Hash)

Redis hash 是一个string类型的field和value的映射表，hash特别适合用于存储对象。

Redis中每个hash可以存储2<sup>32</sup>-1个键值对（40多亿）

###Redis列表(List)

Redis列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）

一个列表最多可以包含2<sup>32</sup>-1个元素 (4294967295, 每个列表超过40亿个元素)。

###Redis集合(Set)
Redis 的 Set 是 String 类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据。

Redis 中集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是 O(1)。

集合中最大的成员数为2<sup>32</sup>-1(4294967295, 每个集合可存储40多亿个成员)。

###Redis有序集合(sorted set)
Redis 有序集合和集合一样也是string类型元素的集合,且不允许重复的成员。

不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。

有序集合的成员是唯一的,但分数(score)却可以重复。

在redis sorted sets里面当items内容大于64的时候同时使用了hash和skiplist两种设计实现。 
添加和删除都需要修改skiplist，所以复杂度为O(log(n))。 
但是如果仅仅是查找元素的话可以直接使用hash，其复杂度为O(1)。
其他的range操作复杂度一般为O(log(n))。
当然如果是小于64的时候，因为是采用了ziplist的设计，其时间复杂度为O(n)。

集合中最大的成员数为2<sup>32</sup>-1(4294967295, 每个集合可存储40多亿个成员)。

##Redis发布订阅

Redis 发布订阅(pub/sub)是一种消息通信模式：发送者(pub)发送消息，订阅者(sub)接收消息。

Redis 客户端可以订阅任意数量的频道。

###Redis事务
Redis 事务可以一次执行多个命令， 
   
批量操作在发送EXEC命令前被放入队列缓存。

收到EXEC命令后进入事务执行，事务中任意命令执行失败，其余的命令依然被执行。

在事务执行过程，其他客户端提交的命令请求不会插入到事务执行命令序列中。

一个事务从开始到执行会经历以下三个阶段：

1.开始事务。

2.命令入队。

3.执行事务。

要注意的是，单个 Redis 命令的执行是原子性的，但 Redis 没有在事务上增加任何维持原子性的机制，所以 Redis 事务的执行并不是原子性的。
      
事务可以理解为一个打包的批量执行脚本，但批量指令并非原子化的操作，中间某条指令的失败不会导致前面已做指令的回滚，也不会造成后续的指令不做。

##Java使用Redis

###安装

需要下载对应的jar包，并添加依赖

###连接

    import redis.clients.jedis.Jedis;
    
    public class Connect {
        public static void main(String[] args) {
            //连接本地的 Redis 服务
            Jedis jedis = new Jedis("localhost");
            System.out.println("连接成功");
            //查看服务是否运行
            System.out.println("服务正在运行: "+jedis.ping());
        }
    }
    
###Key

    import java.util.Iterator;
    import java.util.Set;
    import redis.clients.jedis.Jedis;
    
    public class RedisKeyJava {
        public static void main(String[] args) {
            //连接本地的 Redis 服务
            Jedis jedis = new Jedis("localhost");
            System.out.println("连接成功");
    
            // 获取数据并输出
            Set<String> keys = jedis.keys("*");
            Iterator<String> it=keys.iterator() ;
            while(it.hasNext()){
                String key = it.next();
                System.out.println(key);
            }
        }
    }
    
###String

    import redis.clients.jedis.Jedis;
    
    public class RedisStringJava {
        public static void main(String[] args) {
            //连接本地的 Redis 服务
            Jedis jedis = new Jedis("localhost");
            System.out.println("连接成功");
            //设置 redis 字符串数据
            jedis.set("strkey", "https://github.com/xjwhhh");
            // 获取存储的数据并输出
            System.out.println("redis 存储的字符串为: "+ jedis.get("strkey"));
        }
    }
    
###List

    import java.util.List;
    import redis.clients.jedis.Jedis;
    
    public class RedisListJava {
        public static void main(String[] args) {
            //连接本地的 Redis 服务
            Jedis jedis = new Jedis("localhost");
            System.out.println("连接成功");
            //存储数据到列表中
            jedis.lpush("site-list", "Runoob");
            jedis.lpush("site-list", "Google");
            jedis.lpush("site-list", "Taobao");
            // 获取存储的数据并输出
            List<String> list = jedis.lrange("site-list", 0 ,2);
            for(int i=0; i<list.size(); i++) {
                System.out.println("列表项为: "+list.get(i));
            }
        }
    }
    
##Redis与mysql

redis一般作为缓存使用，mysql作为最终存储

mysql存储在磁盘里，redis存储在内存里，redis既可以用来做持久存储，也可以做缓存。而目前大多数公司的存储都是mysql + redis，mysql作为主存储，redis作为辅助存储，被用作缓存，加快访问读取的速度，提高性能
    
