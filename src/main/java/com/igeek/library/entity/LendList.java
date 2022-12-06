package com.igeek.library.entity;

import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@ToString
public class LendList {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lend_list.ser_num
     *
     * @mbggenerated Mon Sep 26 22:33:44 CST 2022
     */
    private Long serNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lend_list.book_id
     *
     * @mbggenerated Mon Sep 26 22:33:44 CST 2022
     */
    private Long bookId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lend_list.reader_id
     *
     * @mbggenerated Mon Sep 26 22:33:44 CST 2022
     */
    private Long readerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lend_list.lend_date
     *
     * @mbggenerated Mon Sep 26 22:33:44 CST 2022
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String lendDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lend_list.back_date
     *
     * @mbggenerated Mon Sep 26 22:33:44 CST 2022
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String backDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lend_list.ser_num
     *
     * @return the value of lend_list.ser_num
     *
     * @mbggenerated Mon Sep 26 22:33:44 CST 2022
     */
    public Long getSerNum() {
        return serNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lend_list.ser_num
     *
     * @param serNum the value for lend_list.ser_num
     *
     * @mbggenerated Mon Sep 26 22:33:44 CST 2022
     */
    public void setSerNum(Long serNum) {
        this.serNum = serNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lend_list.book_id
     *
     * @return the value of lend_list.book_id
     *
     * @mbggenerated Mon Sep 26 22:33:44 CST 2022
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lend_list.book_id
     *
     * @param bookId the value for lend_list.book_id
     *
     * @mbggenerated Mon Sep 26 22:33:44 CST 2022
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lend_list.reader_id
     *
     * @return the value of lend_list.reader_id
     *
     * @mbggenerated Mon Sep 26 22:33:44 CST 2022
     */
    public Long getReaderId() {
        return readerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lend_list.reader_id
     *
     * @param readerId the value for lend_list.reader_id
     *
     * @mbggenerated Mon Sep 26 22:33:44 CST 2022
     */
    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lend_list.lend_date
     *
     * @return the value of lend_list.lend_date
     *
     * @mbggenerated Mon Sep 26 22:33:44 CST 2022
     */
    public String getLendDate() {
        return lendDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lend_list.lend_date
     *
     * @param lendDate the value for lend_list.lend_date
     *
     * @mbggenerated Mon Sep 26 22:33:44 CST 2022
     */
    public void setLendDate(String lendDate) {
        this.lendDate = lendDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lend_list.back_date
     *
     * @return the value of lend_list.back_date
     *
     * @mbggenerated Mon Sep 26 22:33:44 CST 2022
     */
    public String getBackDate() {
        return backDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lend_list.back_date
     *
     * @param backDate the value for lend_list.back_date
     *
     * @mbggenerated Mon Sep 26 22:33:44 CST 2022
     */
    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }
}