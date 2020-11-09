package vn.hust.edu.kiendao.model;

public class JsonResult {
    private boolean success;

    private Object data;

    public JsonResult() {
    }

    public JsonResult(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }

    public JsonResult jsonsuccess(Object data){
        return new JsonResult(true,data);

    }

    public JsonResult jsonfail(Object data){
        return  new JsonResult(false,data);
    }
}

