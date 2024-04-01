import service from "../utils/request.js";

export function login(data) {
  return service({
    url: "/user/login",
    method: "post",
    data,
  });
}

export function logout() {
  return service({
    url: "/user/logout",
    method: "get",
  });
}

export function getUserInfo() {
  return service({
    url: "/user/userInfo",
    method: "get",
  });
}

export function getUserInfoById(id) {
  return service({
    url: `/user/userInfo/${id}`,
    method: "get",
  });
}
//加入指定房间Api
export function join(data) {
  return service({
    url: `/competition/join/${data}`,
    method: "get",
  });
}
//退出房间Api
export function quit(roomId){
  return service({
    url: `/competition/quit/${roomId}`,
    method: "get"
  })
}
//创建比赛房间Api
export function createRoom() {
  return service({
    url: "/competition/create",
    method: "post",
  });
}
//开始对战
export function startCompetition(roomId){
  return service({
    url:`/competition/start/${roomId}`,
    method:"post"
  })
}
//提交
export function submitCodeApi(data){
  return service({
    url:`/competition/submit`,
    method:"post",
    data
  })
}
//准备
export function prepare(roomId){
  return service({
    url:`/competition/prepare/${roomId}`,
    method:"post"
  })
}

export function showImage() {
  return service({
    url: "/user/showImage",
    method: "get",
  });
}

// 对战Api
export function getNavData() {
  return service({
    url: "/user/navData",
    method: "get",
  });
}

// 历史记录Api
export function getHistory() {
  return service({
    url: "/history",
    method: "get",
  });
}

// 查询本次对局记录Api
export function getHistoryOne(data) {
  return service({
    url: "/historyOne",
    method: "post",
    data
  });
}

export function getAllRanking() {
  return service({
    url: "/ranking",
    method: "get",
  });
}

//天梯对战接口
export function createLadderRoom() {
  return service({
    url: "/ladder/create",
    method: "get",
  });
}

export function joinLadderRoom(data) {
  return service({
    url: `/ladder/join/${data}`,
    method: "get",
  });
}

export function quitLadderRoom(data) {
  return service({
    url: `/ladder/quit/${data}`,
    method: "get",
  });
}

export function ladderPrepare(data) {
  return service({
    url: `/ladder/prepare/${data}`,
    method: "get",
  });
}

export function startLadderCompetition(data) {
  return service({
    url: `/ladder/start/${data}`,
    method: "get",
  });
}

export function submitLadderTitle(data) {
  return service({
    url: `/ladder/submit`,
    method: "post",
    data
  });
}

export function getLadderHistory(data) {
  return service({
    url: `/ladder/historyOne`,
    method: "post",
    data
  });
}

