package org.zerock.fc.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {

    @Builder.Default // 기본으로 1페이지에 10개로 build해줌
    //기본생성자 느낌 객체생성시 디폴트값 준다.
    private int page = 1;
    @Builder.Default
    private int size = 10;

    //get , set메서드를 기본으로 동작한다.

    public int getSkip(){
        //1페이지 - 0   1(페이지) - 1 * 10(가져올 갯수번호까지)
        //2페이지 - 10 2페이지는 뒤에 10건의 데이터를 스킵한다.
        //3페이지 - 20 1페이지 10개+2페이지 10개 = 20개 스킵
        return (this.page -1) * size;
    }
}
