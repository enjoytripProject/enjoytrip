<template>
  <div id="kakao_map">
    <div id="map" class="map"></div>
    <!-- plan의 주인과 로그인한 username이 일치하면 custom-->
    <div class="side-bar" v-if="isMine()">
      <CustomLeftBar
        v-if="kakaomap != null && map != null"
        :kakaomap="kakaomap"
        :map="map"
        class="left-side-bar" />
      <CustomRightBar :map="map" class="right-side-bar" />
    </div>
    <!-- plan의 주인과 username이 다르면 read -->
    <div class="side-bar" v-else>
      <ReadLeftBar
        v-if="kakaomap != null && map != null"
        :kakaomap="kakaomap"
        :map="map"
        class="left-side-bar" />
      <ReadRightBar :map="map" class="right-side-bar" />
    </div>
  </div>
</template>

<script>
import CustomLeftBar from "./Plan/CustomLeftBar.vue";
import CustomRightBar from "./Plan/CustomRightBar.vue";
import ReadLeftBar from "./Plan/ReadLeftBar.vue";
import ReadRightBar from "./Plan/ReadRightBar.vue";
import http from "@/util/http.js";
export default {
  name: "KakaoMap",
  data() {
    return {
      // DB에다가 옮길 준비 해야함
      restApiKey: "e7b0d28b60cea6a7d5b5b92a4afdcef4",
      javaScriptKey: "072af5d564b2fe0e2778a38436435f49",
      nativeAppKey: "9d29d54a5264a72919fa63d8292496d2",
      adminKey: "6cba4894e7a3c252933edb6d1e69a1e1",
      kakaomap: null,
      map: null,
      planUsername: "", // plan 제작 유저이름
    };
  },
  components: {
    CustomLeftBar,
    CustomRightBar,
    ReadLeftBar,
    ReadRightBar,
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      this.kakaomap = container;
      const options = {
        // 처음 지도의 위치를 lat, lng(위도, 경도) 값으로 설정한다.
        center: new kakao.maps.LatLng(33.450701, 126.570667),

        level: 8,
      };

      this.map = new kakao.maps.Map(container, options);
    },
    isMine() {
      if (this.planUsername === this.userInfo.username) return true;
      else if (!this.planUsername) return true;
      else return false;
    },
  },
  computed: {
    userInfo() {
      return this.$store.state.userInfo;
    },
  },
  created() {
    this.planUsername = this.$route.params.username;
    if (this.planUsername) {
      http
        .get("/attraction/myplanList/" + this.$route.params.plan_id)
        .then((res) => {
          this.$store.state.planTitle = res.data.planTitle;
          this.$store.state.plan = res.data.plan;
        });
    }
  },
  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.type = "text/javascript";
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=072af5d564b2fe0e2778a38436435f49&libraries=clusterer,drawing,services&autoload=false";
      /* eslint를 사용한다면 kakao 변수가 선언되지 않았다고
       * 오류를 내기 때문에 아래 줄과 같이 전역변수임을
       * 알려주어야 한다. */
      http.get("/attraction").then((res) => {
        this.attractions = res.data;
        this.$store.attractions = this.attractions;
      });
      /* global kakao */
      script.addEventListener("load", () => {
        kakao.maps.load(() => {
          // 카카오맵 API가 로딩이 완료된 후 지도의 기본적인 세팅을 시작해야 한다.
          this.initMap();
        });
      });
      document.head.appendChild(script);
    } else {
      // 이미 카카오맵 API가 로딩되어 있다면 바로 지도를 생성한다.
      this.initMap();
    }
  },
};
</script>

<style lang="scss">
#kakao_map {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  > .side-bar {
    > .left-side-bar {
      position: absolute;
      left: 0;
      z-index: 10; // 띄울 때 우선 순위 : 클 수록 우선순위가 높다.
    }
    > .right-side-bar {
      position: absolute;
      right: 0;
      z-index: 11;
    }
  }
  > .map {
    position: absolute;
    width: 100%;
    height: 100%;
  }
}
</style>
