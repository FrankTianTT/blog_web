<template>
    <div class="me-view-comment-item">
        <div class="me-view-comment-author">
            <a class="">
                <img class="me-view-picture" :src="avatar"/>
            </a>
            <div class="me-view-info">
                <span class="me-view-nickname">{{authorName}}</span>
                <div class="me-view-meta">
                    <span>{{id}}楼</span>
                    <span>{{datetime}}</span>
                </div>
            </div>
        </div>
        <div >
            <p class="me-view-comment-content">{{content}}</p>
        </div>
    </div>
</template>

<script>
    import defaultAvatar from '@/assets/img/default_avatar.png'

    export default {
        name: "CommentItem",
        props:{
            id:Number,
            authorName:String,
            content:String,
            datetime:String,
        },
        data() {
            return {
                avatar:defaultAvatar,

            }
        }
    }
</script>

<style>
    .me-view-tag-item {
        margin: 0 4px;
    }

    .me-view-comment {
        margin-top: 60px;
    }

    .me-view-comment-title {
        font-weight: 600;
        border-bottom: 1px solid #f0f0f0;
        padding-bottom: 20px;
    }

    .me-view-comment-write {
        margin-top: 20px;
    }

    .me-view-comment-text {
        font-size: 16px;
    }

    .v-show-content {
        padding: 8px 25px 15px 0px !important;
    }

    .v-note-wrapper .v-note-panel {
        box-shadow: none !important;
    }

    .me-view-comment-item {
        margin-top: 20px;
        padding-bottom: 16px;
        border-bottom: 1px solid #f0f0f0;
    }

    .me-view-comment-author {
        margin: 10px 0;
        vertical-align: middle;
        float: left;
    }

    .me-view-nickname {
        font-size: 14px;
    }

    .me-view-comment-content {
        line-height: 1.5;
    }

    .me-view-comment-tools {
        margin-top: 4px;
        margin-bottom: 10px;
    }

    .me-view-comment-tool {
        font-size: 13px;
        color: #a6a6a6;
        padding-right: 14px;
    }

    .v-note-wrapper .v-note-panel .v-note-show .v-show-content, .v-note-wrapper .v-note-panel .v-note-show .v-show-content-html {
        background: #fff !important;
    }

    .me-reply-list {
        padding-left: 16px;
        border-left: 4px solid #c5cac3;
    }

    .me-reply-item {
        margin-bottom: 8px;
        border-bottom: 1px solid #f0f0f0;
    }

    .me-reply-user {
        color: #78b6f7;
    }
</style>
