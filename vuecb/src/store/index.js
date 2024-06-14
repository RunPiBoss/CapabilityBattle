import { defineStore } from "pinia";
export const mainStore = defineStore("main", {
  state() {
    return {
      nav: [],
      isWin:'',
      competitionData:{}
    };
  },
  getters: {},
  actions: {
    setNav(data) {
      this.nav = data;
    },
    setIsWin(status){
      this.isWin = ''
      this.isWin = status
    },
    setCompetitionData(data){
      this.competitionData = data
    }
  },
});
