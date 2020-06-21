<template>
    <a-modal
            :visible="updateUserInfoModalVisible"
            title="更改个人信息"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="用户名">
                <a-input
                        placeholder="请填写用户名"
                        v-decorator="['userName', { rules: [{ required: true, message: '请填写用户名' }],initialValue: userInfo.userName }]"
                />
            </a-form-item>
            <a-form-item label="QQ号" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写QQ号"
                        v-decorator="['qqNumber', { rules: [{ required: true, message: '请填写QQ号' }],initialValue: userInfo.qqNumber  }]"
                />
            </a-form-item>

            <a-form-item label="密码" v-bind="formItemLayout">
                <a-input-password
                        placeholder="请填写密码"
                        v-decorator="['password', { rules: [{ required: true, message: '请输入密码' }],initialValue: userInfo.password}]"
                />
            </a-form-item>
            <a-form-item label="个人简介" v-bind="formItemLayout">
                <a-input
                        type="textarea"
                        :rows="4"
                        placeholder="请填写个人简介"
                        v-decorator="['profile', { rules: [{ required: true, message: '请填写个人简介' }] ,initialValue:userInfo.profile}]"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'updateUserInfoModal',
        components:{
        },
        data() {
            return {
                formItemLayout: {
                    labelCol: {
                        xs: { span: 12 },
                        sm: { span: 6 },
                    },
                    wrapperCol: {
                        xs: { span: 24 },
                        sm: { span: 16 },
                    },
                },
            }
        },
        computed: {
            ...mapGetters([
                'userInfo',
                'updateUserInfoModalVisible',
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'updateUserInfoModal' });

        },
        mounted() {
            console.log(this.userInfo)
        },
        methods: {
            ...mapMutations([
                'set_updateUserInfoModalVisible',
            ]),

            ...mapActions([
                'updateUserInfo'
            ]),
            cancel() {
                this.set_updateUserInfoModalVisible(false)
            },
            changeStar(v){

            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data={
                            password:this.form.getFieldValue('password'),
                            userName:this.form.getFieldValue('userName'),
                            profile:this.form.getFieldValue('profile'),
                            qqNumber:this.form.getFieldValue('qqNumber'),
                        }
                        console.log(data)
                        this.updateUserInfo(data)
                        this.set_updateUserInfoModalVisible(false)
                        //location.reload()
                    }
                });
            },
        }
    }
</script>