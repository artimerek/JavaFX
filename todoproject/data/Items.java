package todoproject.data;

import java.time.LocalDate;

public class Items {
    private LocalDate deadline;
    private String shortDescription;
    private String fullDescription;

    public Items(LocalDate deadline, String shortDescription, String fullDescription) {
        this.deadline = deadline;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }
}
