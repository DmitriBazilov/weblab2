package com.dmitri.ifmo_web_lab_2.model;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import com.dmitri.ifmo_web_lab_2.database.DatabaseConnector;
import com.dmitri.ifmo_web_lab_2.dto.HitCheckDTO;
import com.dmitri.ifmo_web_lab_2.util.SessionGetter;
import com.dmitri.ifmo_web_lab_2.repository.*;
import com.dmitri.ifmo_web_lab_2.util.AreaChecker;
import java.time.ZonedDateTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ManagedBean
@RequestScoped
public class HitCheck implements Serializable {
   
    @ManagedProperty(value = "#{hitCheckRepositoryImpl}", name = "hitCheckRepository")
    private HitCheckRepository hitCheckRepository;

    @ManagedProperty(value = "#{table}", name = "table")
    private Table table;

    @ManagedProperty(value = "#{areaChecker}", name = "areaChecker")
    private AreaChecker areaChecker;

    @ManagedProperty(value = "#{sessionGetter}", name = "sessionGetter")
    private SessionGetter sessionGetter;

    private Double x;
    private Double y;
    private Double r;
    private Instant currentTime = Instant.now();
    private Long executeTime;
    private Boolean result;
    private Integer timezone;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public Instant getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Instant currentTime) {
        this.currentTime = currentTime;
    }

    public Long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public HitCheckRepository getHitCheckRepository() {
        return hitCheckRepository;
    }

    public void setHitCheckRepository(HitCheckRepository hitCheckRepository) {
        this.hitCheckRepository = hitCheckRepository;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public AreaChecker getAreaChecker() {
        return areaChecker;
    }

    public void setAreaChecker(AreaChecker areaChecker) {
        this.areaChecker = areaChecker;
    }

    public SessionGetter getSessionGetter() {
        return sessionGetter;
    }

    public void setSessionGetter(SessionGetter sessionGetter) {
        this.sessionGetter = sessionGetter;
    }

    public String getFancyResult() {
        return result ? "Попадание" : "Промах";
    }

    public void save() {
        saveTimezone(timezone);
        result = areaChecker.checkHitInArea(this);
        executeTime = System.currentTimeMillis() - currentTime.toEpochMilli();
        HitCheckDTO dto = new HitCheckDTO();
        dto.setX(x);
        dto.setY(y);
        dto.setR(r);
        dto.setHitDate((currentTime.atZone(ZoneId.of("UTC"))).plusHours(timezone));
        dto.setExecuteTime(executeTime);
        dto.setResult(result);
        dto.setSessionId(sessionGetter.getSessionId());
        List<HitCheck> hits = table.getHitsBySessionId(sessionGetter.getSessionId());
        hits.add(this);
        table.getHits().put(sessionGetter.getSessionId(), hits);
        hitCheckRepository.add(dto);
    }

    public void removeSessionDots(String sessionId) {
        table.removeSessionDots(sessionId);
        hitCheckRepository.removeHitsBySessionId(sessionId);
    }

    public String getCurrentTimeAsString() {
        System.out.println(timezone);
        System.out.println(getSessionTimezone());
        return currentTime.atZone(ZoneId.of("UTC"))
                .plusHours(getSessionTimezone())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    } 

    public void saveTimezone(Integer timezone) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("timezone", timezone);
    }

    public Integer getSessionTimezone() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().putIfAbsent("timezone", 0);
        return (Integer) context.getExternalContext().getSessionMap().get("timezone");
    }

    public void validateY(FacesContext context, UIComponent comp, Object value) {
        if (value == null) {
            ((UIInput) comp).setValid(false);
            FacesMessage message = new FacesMessage("Введите Y");
            context.addMessage(comp.getClientId(context), message);
        } else {
            try {
                y = (double) value;
            } catch (Throwable ex) {
                ((UIInput) comp).setValid(false);
                FacesMessage message = new FacesMessage("Введите число");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                context.addMessage(comp.getClientId(context), message);
            }
        }
    }
}
