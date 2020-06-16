const getters = {
  //user
  token: state => state.user.token,
  userId: state => state.user.userId,
  userInfo: state => state.user.userInfo,
  userName:state=>state.user.userInfo.userName,
  userArticleList: state=>state.user.userArticleList,
  userCommentList:state=>state.user.userCommentList,
  articleNeedModify:state=>state.user.articleNeedModify,
  articleFormNeedModify:state=>state.user.articleFormNeedModify,
  //admin
  userList:state => state.admin.userList,
  addUserModalVisible: state => state.admin.addUserModalVisible,
  addUserParams: state => state.admin.addUserParams,
  editUserModalVisible:state => state.admin.editUserModalVisible,
  currentUserInfo:state => state.admin.currentUserInfo,
  articleList:state => state.admin.articleList,
  commentsList:state=>state.admin.commentsList
  }
  
  export default getters