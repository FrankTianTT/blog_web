<template>
    <div>
        <base-header/>
        <router-view/>
    </div>
</template>

<script>
import BaseHeader from '@/components/BaseHeader'
export default {
    name:"Layout",
    components:{
        BaseHeader
    }
}
</script>