//import com.igeek.library.Vo.ReaderLendVo;
//import com.igeek.library.config.Quartz;
//import com.igeek.library.config.RunA;
//import com.igeek.library.entity.Admin;
//import com.igeek.library.entity.BookInfo;
//import com.igeek.library.entity.LendList;
//import com.igeek.library.entity.ReaderInfo;
//import com.igeek.library.mapper.AdminMapper;
//import com.igeek.library.mapper.BookInfoMapper;
//import com.igeek.library.mapper.LendListMapper;
//import com.igeek.library.mapper.ReaderInfoMapper;
//import com.igeek.library.utils.DateUtils;
//import com.igeek.library.utils.PasswordUtils;
//import org.junit.Test;
//
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.annotation.Resource;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
//public class TestAdmin {
//    @Autowired
//    AdminMapper adminMapper;
//    @Autowired
//    BookInfoMapper bookInfoMapper;
//    @Autowired
//    LendListMapper lendListMapper;
//    @Autowired
//    ReaderInfoMapper readerInfoMapper;
//    @Test
//    public void test1(){
//        Admin admin = adminMapper.findAdminByName("admin");
//        System.out.println(admin.getUsername());
//    }
//    @Test
//    public void test2(){
//        List<BookInfo> books = bookInfoMapper.findAllByLike("人");
//        System.out.println(books);
//    }
//    @Test
//    public void test3(){
//        LendList lendList = new LendList();
//        lendList.setLendDate(DateUtils.createDate());
//        lendList.setBookId(3L);
//        lendList.setReaderId(1l);
//        Integer integer = lendListMapper.addLend(lendList);
//        System.out.println(integer);
//    }
//    @Test
//    public void test4(){
//        List<ReaderLendVo> readerLendVoById = lendListMapper.findReaderLendVoById(10000);
//        readerLendVoById.forEach(System.out::println);
//    }
//    @Test
//    public void test5(){
//        ReaderInfo readerInfo = new ReaderInfo();
//        readerInfo.setReaderId(null);
//        readerInfo.setAddress("jjjjj");
//        readerInfo.setBirth("2001-01-02");
//        readerInfo.setName("你们的");
//        readerInfo.setPhone("12321312");
//        readerInfo.setSex("男");
//        readerInfoMapper.addReader(readerInfo);
//        System.out.println(readerInfo);
//    }
//
//
//    @Test
//    public void test7(){
//        String uuid = UUID.randomUUID().toString();
//        System.out.println(uuid);
//        System.out.println(PasswordUtils.getMd5Password("123", uuid));
//
//    }
//
//
//}
