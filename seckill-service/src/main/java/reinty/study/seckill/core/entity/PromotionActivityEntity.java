package reinty.study.seckill.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_promotion_activity")
public class PromotionActivityEntity extends BaseAutoIdEntity {
    /**
     * 活动名称
     */
    private String promotionName;
    /**
     * 活动开始时间
     */
    private long startTime;
    /**
     * 活动结束时间
     */
    private long endTime;

    /**
     * 是否在前端展示  0 - 不展示 1 - 展示
     */
    private int shows;

    /**
     * 0 - 赠品或单优惠券促销   1-优惠券大促
     */
    private int type = 0 ;

    /**
     * 拓展字段
     */
    private String conf;

    @Column(name = "promotion_name")
    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    @Column(name = "shows")
    public int getShows() {
        return shows;
    }

    public void setShows(int shows) {
        this.shows = shows;
    }


    @Column(name = "start_time")
    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @Column(name = "end_time")
    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }


    @Column(name = "conf")
    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
