package com.SberProjectUEN.java13springTU;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Java13SpringProjectApplication
      implements CommandLineRunner {
//    private BookDaoBean bookDaoBean;

//    @Autowired
//    public void setBookDaoBean(BookDaoBean bookDaoBean) {
//        this.bookDaoBean = bookDaoBean;
//    }
    
//    @Autowired
//    private NamedParameterJdbcTemplate jdbcTemplate;
//
//    public Java13SpringProjectApplication(BookDaoBean bookDaoBean) {
//        this.bookDaoBean = bookDaoBean;
//    }
    
    public static void main(String[] args) {
        SpringApplication.run(Java13SpringProjectApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
//        BookDaoJDBC bookDaoJDBC = new BookDaoJDBC();
//        bookDaoJDBC.findBookById(1);

//        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyDBConfigContext.class);
//        BookDaoBean bookDaoBean = ctx.getBean(BookDaoBean.class);
//        bookDaoBean.findBookById(1);
//        bookDaoBean.findBookById(1);
//
//        List<Book> books = jdbcTemplate.query("select * from books",
//                                              (rs, rowNum) -> new Book(
//                                                    rs.getInt("id"),
//                                                    rs.getString("title"),
//                                                    rs.getString("author"),
//                                                    rs.getDate("date_added")
//                                              ));
//        books.forEach(System.out::println);
    }
}
