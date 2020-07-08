package reinty.study.seckill.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_order")
public class OrderEntity extends BaseAutoIdEntity {

    private static final long serialVersionUID = -1231236218825976063L;

    /**
     * 用户id
     */
    private long userId;
    /**
     * 订单号 唯一
     */
    private String orderNo;
    /**
     * 总价 = 单价 * 数量
     */
    private long totalPrice;


    /**
     * 购买数量
     */
    private int count;

    /**
     * 主订单状态
     */
    private int stat;
    /**
     * 实际应付款
     */
    private long actualPrice;
    /**
     * 运费
     */
    private long expressPrice;
    /**
     * 折扣价
     */
    private long discountPrice;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 支付成功时间
     */
    private long payTime;
    /**
     * 优惠券账户id
     */
    private long couponAccountId;
    /**
     * 优惠券 优惠金额
     */
    private long couponPrice;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 订单标记，请在 OrderMarkEnum 枚举维护相应标记，此标记适用于打上标之后不会再取消的，可以用 cancelMark 取消对应的标位
     */
    private long mark;

    /**
     * 商品单价
     */
    private long unitPrice;
    /**
     * 收货人手机
     */
    private String expressPhone;

    /**
     * 收货人姓名
     */
    private String expressName;
    /**
     * 类型
     */
    private int type;
    /**
     * conf 拓展字段
     */
    private String conf;
    /**
     * 买家留言
     */
    private String buyerRemarks;
    /**
     * 订单创建 类型 0 普通订单 1开团订单 2参团订单
     */
    private int createType;

    private byte unfilledAddressFlag = 0;     // 未填写地址标记，0-否，1-是

    private String contractPhone;       // 联系人电话

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 父订单ID
     */
    private String parentNo;

    @Column(name = "coupon_price")
    public long getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(long couponPrice) {
        this.couponPrice = couponPrice;
    }

    @Column(name = "coupon_account_id")
    public long getCouponAccountId() {
        return couponAccountId;
    }

    public void setCouponAccountId(long couponAccountId) {
        this.couponAccountId = couponAccountId;
    }

    @Column(name = "pay_time")
    public long getPayTime() {
        return payTime;
    }

    public void setPayTime(long payTime) {
        this.payTime = payTime;
    }

    @Column(name = "express_price")
    public long getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(long expressPrice) {
        this.expressPrice = expressPrice;
    }

    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "order_no")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "total_price")
    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Column(name = "stat")
    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    @Column(name = "actual_price")
    public long getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(long actualPrice) {
        this.actualPrice = actualPrice;
    }

    @Column(name = "discount_price")
    public long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(long discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Column(name = "goods_name")
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Column(name = "mark")
    public long getMark() {
        return mark;
    }

    public void setMark(long mark) {
        this.mark = this.mark | mark;
    }

    /**
     * 取消标位
     * @param mark 要取消的标位
     */
    public void cancelMark(long mark){
        this.mark = this.mark & ~mark;
    }

    @Column(name = "count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Column(name = "create_type")
    public int getCreateType() {
        return createType;
    }

    public void setCreateType(int createType) {
        this.createType = createType;
    }

    @Column(name = "express_phone")
    public String getExpressPhone() {
        return expressPhone;
    }

    public void setExpressPhone(String expressPhone) {
        this.expressPhone = expressPhone;
    }

    @Column(name = "express_name")
    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Column(name = "conf")
    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    @Column(name = "buyer_remarks")
    public String getBuyerRemarks() {
        return buyerRemarks;
    }

    public void setBuyerRemarks(String buyerRemarks) {
        this.buyerRemarks = buyerRemarks;
    }

    @Column(name = "unfilled_address_flag")
    public byte getUnfilledAddressFlag() {
        return unfilledAddressFlag;
    }

    public void setUnfilledAddressFlag(byte unfilledAddressFlag) {
        this.unfilledAddressFlag = unfilledAddressFlag;
    }

    @Column(name = "contract_phone")
    public String getContractPhone() {
        return contractPhone;
    }

    public void setContractPhone(String contractPhone) {
        this.contractPhone = contractPhone;
    }

    @Column(name = "goods_code")
    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    @Column(name = "unit_price")
    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Column(name = "parent_no")
    public String getParentNo() {
        return parentNo;
    }

    public void setParentNo(String parentNo) {
        this.parentNo = parentNo;
    }
}
