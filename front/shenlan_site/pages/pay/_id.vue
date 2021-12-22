<template>
  <div class="cart py-container">
    <!--主内容-->
    <div class="checkout py-container  pay">
      <div class="checkout-tit" style="width: 1050px; margin: 0 auto; padding: 10px 0;">
        <h4 class="fl tit-txt"><span class="success-info">支付申请提交成功，请您及时付款！订单号：{{ payObj.out_trade_no }}</span>
        </h4>
        <span class="fr"><em class="sui-lead">应付金额：</em><em class="orange money">￥{{
          payObj.total_fee / 100
        }}</em></span>
        <div class="clearfix"/>
      </div>
      <div class="checkout-steps">
        <div class="fl weixin">微信支付</div>
        <div class="fl sao">
          <div class="fl code">
            <!-- <img id="qrious" src="~/assets/img/erweima.png" alt=""> -->
            <qriously :value="payObj.code_url" :size="338"/>
          </div>
          <div style="color: red; text-align:center;">请使用微信扫一扫</div>
        </div>
        <div class="clearfix"/>
        <!-- <p><a href="pay.html" target="_blank"> 其他支付方式</a></p> -->
      </div>
    </div>

  </div>
</template>
<script>
import payApi from '~/api/pay'
import orderApi from '~/api/order'

export default {
  async asyncData(page) {
    const response = await payApi.createNative(page.route.params.id)
    return {
      payObj: response.data
    }
  },

  // 在 created 中获取数据，报告 Invalid prop: type check failed for prop "value".
  // created() {
  //   payApi.createNative(this.$route.params.id).then(response => {
  //     this.payObj = response.data
  //   })
  // }
  data() {
    return {
      timer: null // 定时器
    }
  },

  // created 的时候就查询支付状态，没有必要，因为二维码页面尚未渲染，不可能支付成功
  mounted() {
    // 启动定时器
    this.timer = setInterval(() => {
      this.queryPayStatus()
    }, 3000)
  },

  methods: {

    // 查询订单状态
    queryPayStatus() {
      orderApi.queryPayStatus(this.payObj.out_trade_no)
        .then(response => {
          console.log('查询订单状态：' + response.code)

          // 支付成功后的页面跳转
          if (response.success) {
            this.$message.success(response.message)
            console.log('清除定时器')
            clearInterval(this.timer)
            // 三秒后跳转到课程详情页面观看视频
            setTimeout(() => {
              this.$router.push({ path: '/course/' + this.payObj.course_id })
            }, 3000)
          }
        })
    }
  }
}
</script>
