package br.com.serratec.lojavirtual.error;

public class ErrorMessage {
    
    private String title;
    private Integer status;
    private String detail;
    private String developerMessage;
    private Long timestamp;
    
    public ErrorMessage(String title, Integer status, String detail, String developerMessage, Long timestamp) {
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.developerMessage = developerMessage;
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    
}
