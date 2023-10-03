package com.alura.latam.forum.domain.topic;

import com.alura.latam.forum.domain.course.Course;
import com.alura.latam.forum.domain.response.Response;
import com.alura.latam.forum.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    
    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public StatusTopic getStatus() {
		return status;
	}

	public void setStatus(StatusTopic status) {
		this.status = status;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	@Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusTopic status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_author")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_course")
    private Course course;

    private Boolean active;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
    private List<Response> responses = new ArrayList<>();


    public Topic(String title, String message, LocalDateTime creationDate, User author, Course course) {
        this.title = title;
        this.message = message;
        this.creationDate = creationDate;
        this.status = StatusTopic.NO_RESPONDIDO;
        this.author = author;
        this.course = course;
        this.active = true;
    }

    public void updateTopic(String title, String message, StatusTopic status, Course course) {

        if (title != null) {
            this.title = title;
        }
        if (message != null) {
            this.message = message;
        }
        if(status != null) {
            this.status = status;
        }
        if(course != null) {
            this.course = course;
        }
    }

    public void deleteTopic() {
        this.active = false;
    }
}
