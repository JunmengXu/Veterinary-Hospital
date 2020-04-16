import zhLocale from "element-ui/lib/locale/lang/zh-CN";
const cn = {
    menu : {
        home:"首页",
        customerPetpagePetname: "宠物名称: ",
        register:"用户注册",
        customerLogin:"客户系统登录",
        staffLogin:"员工系统登录",
        title:"动物医院预约系统",
        addAppointment:"添加预约",
        addPet:"添加宠物",
        contactStaff:"联系员工",
        staffHome:"动物医院员工后台管理系统",
        urgentAppointment:"紧急预约",
        normalAppointment:"普通预约",
        setOperationTime:"设置手术时间",
    },
    message:{
        alreadyExist:"用户已存在，请重试！",
        exit:"退出成功",
        symptom:"请输入宠物症状",
        symptomLength:"长度在 1 到 100 个字符",
        urgency:"请至少选择一个紧急度",
        time:"请选择日期",
        hospital:"请至少选择一个医院",
        successed:"成功",
        failed:"失败",
        successAppointment:"成功创建新的预约!",
        failingAppointment:"创建预约失败!",
        successAssign:"成功修改手术日期! ",
        failingAssign:"修改手术日期失败!",
    },
    placeholder:{
        account:"请填写账号",
        password:"请填写密码",
        email:"请填写邮箱",
        phone:"请填写手机号",
        symptom: "请输入宠物症状",
        urgency: "请选择紧急度",
        time:"请选择日期",
        hospital:"请选择医院",
        searchUser:"请输入用户名进行搜索",

    },
    button:{
        register:"注册",
        login:"登录",
        goRegister:"还没有账号，先去注册",
        staffEntrance:"员工入口",
        customerEntrance:"客户入口",
        customerHomepagePet:"我的宠物",
        customerHomepageAppointment: "我的预约单",
        customerHomepageMessage: "私信",
        logOut:"退出登录",
        switchLanguage:"语言切换",
        add:"添加",
        reset:"重置",
        cancel:"取消",
        allAppointments:"所有预约单",
        customerMessages:"客户消息",
        schedule:"时间表",
        detail:"详情",
        sendMessage:"私信",
        assign:"分配",

    },
    sideMenu:{
        all:"全部",
        waitingDistribution:"待审批",
        waitingOperation:"待手术",
        duringOperation:"手术中",
        waitingRelease:"待释放",
        noAppointment:"无预约",
        assigned:"已分配",
        unassigned:"未分配",
    },
    column:{
        English:"英语",
        Chinese:"中文",
        petNumber:"编号: ",
        petName:"名字: ",
        petGender:"性别: ",
        petType:"种类: ",
        owner:"主人: ",
        symptom:"症状: ",
        urgency:"紧急度: ",
        time:"需求时间: ",
        hospital:"所选医院: ",
        pet:"宠物：",
        distribution:"分配情况： ",
        appointmentNumber:"编号: ",
        operation:"操作：",
        phone:"联系电话: ",
        reserveTime:"下单时间: ",
        date:"日期: ",
        customer:"客户: ",
        newMessage:"最新消息: ",
<<<<<<< HEAD
        customerInfo:" 客户信息: ",
        petInfo:" 宠物信息: ",

=======
        customerInfo: "客户信息",
        petInfo: "宠物信息",
        symptomInfo: "病症描述",
        bookingMadeTime:"下单时间",
        bookingNeedTime:"预约时间"
>>>>>>> 8b83b7d733f1e5c0d44455f4296dc91d17a12064
    },
    choices:{
        normal:"普通",
        urgent:"紧急",
        Beijing:"北京",
        Shanghai:"上海",
        Chengdu:"成都",
        male:"公",
        female:"母",
    },
    ...zhLocale
};

export default cn;