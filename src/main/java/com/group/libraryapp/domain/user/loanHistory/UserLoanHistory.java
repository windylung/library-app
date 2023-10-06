package com.group.libraryapp.domain.user.loanHistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne //UserLoanHistory 입장에서는 기록 여러 개 > user 1명 (N:1)
    // userId가 User 테이블을 가리키고 있으므로 > 연관 관계의 주인
    private User user;
    private String bookName;
    private boolean isReturn;

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }
    protected UserLoanHistory(){}

    public void updateIsReturn(boolean isReturn){
        this.isReturn = isReturn;
    }

    public void doReturn(){
        this.isReturn = true;
    }

    public String getBookName() {
        return bookName;
    }
}
