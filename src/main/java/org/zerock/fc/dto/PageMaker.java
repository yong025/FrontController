package org.zerock.fc.dto;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageMaker {

    private int start,page,end,size,total;
    private boolean prev,next;

   public PageMaker(int page, int size,int total){//현재 페이지와 출력갯수, 총 글 수를 가져온다.

       this.page = page;
       this.size = size;
       this.total = total;

        //마지막 페이지
       end = (int)(Math.ceil(page / 10.0) * 10); //(현재 페이지의 나누기 10 후 올림처리) 후에 곱하기 10 = 내가 있는 페이지의 끝 페이지
       start = end -9;//start(시작페이지)는 마지막페이지 -9

        prev = start  > 1;
        next = (total / (double)size) > end;
        // total이 123개로 가정하고 size가 10개씩 출력이라면 12.3개
        // 이 값이 end보다 크면 next(다음 페이지 이동)가 된다.

       if(end * size > total){//끝페이지 * 페이지내 출력한 갯수 > 총 데이터갯수라면 (13 * 10) > 123
           end = (int)(Math.ceil(total/(double)size));// (총 데이터갯수 나누기 페이지내 출력한 갯수 후 올림)  (123 / 10)후 올림은 13) 를 마지막페이지로 정의한다.
       }

       //end = end * size > total ? (int)(Math.ceil(total/(double)size)) :
       // end ; 3항 연산자로도 가능 if와의 차이점은 연산자이기 때문에 값을 만들어내야만 사용 가능
    }
}
