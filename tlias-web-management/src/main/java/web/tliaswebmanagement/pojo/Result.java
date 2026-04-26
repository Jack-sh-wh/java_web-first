package web.tliaswebmanagement.pojo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonPropertyOrder({ "code", "message", "data" })
public class Result {
    private Integer code;
    private String message;
    private Object data;


    public static Result success() {
        Result r = new Result();
        r.code = 1;
        r.message = "操作成功";
        return r;
    }

    public static Result success(String message) {
        Result r = new Result();

        r.code = 0;
        r.message = message;

        return r;
    }




    public static Result success(Object data) {
        Result r = new Result();
        r.message = "操作成功";
        r.code = 1;
        r.data = data;

        return r;
    }


    public static Result error(String s) {
        Result r = new Result();
        r.code = 0;
        r.message = s;
        return r;
    }
}
