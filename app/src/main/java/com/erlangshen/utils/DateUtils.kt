package com.erlangshen.utils

import com.erlangshen.utils.DateUtils.Companion.convert2SqlDate
import java.sql.Date
import java.sql.Timestamp
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.regex.Pattern

class DateUtils {

    /**
     * 获取系统时间 精确到毫秒数
     *
     * @return
     */
    val sysDateForServer: String
        get() {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
            return sdf.format(java.util.Date())
        }

    companion object {

        fun StringToDate(s: String?): Date? {
            var d = java.util.Date()

            val date: Date

            if (s == null)
                return null

            d = java.util.Date(s)

            date = Date(d.time)

            return date
        }

        fun StringToDate1(time: String): Date {
            var d = java.util.Date()
            val date: Date
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            try {
                d = sdf.parse(time)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            date = Date(d.time)

            return date
        }

        fun StringToDate2(time: String): Date {
            var d = java.util.Date()
            val date: Date
            val sdf = SimpleDateFormat("yyyyMMdd")
            try {
                d = sdf.parse(time)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            date = Date(d.time)

            return date
        }

        val sysYear: Int
            get() {
                val d = java.util.Date()

                val date = Date(d.time)

                return date.year
            }

        val sysMonth: Int
            get() {
                val d = java.util.Date()

                val date = Date(d.time)

                return date.month
            }

        val sysDay: Int
            get() {
                val d = java.util.Date()

                val date = Date(d.time)

                return date.day
            }

        val sysDate: String
            get() {
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                return sdf.format(java.util.Date())
            }

        val sysTime: String
            get() {
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                return sdf.format(java.util.Date())
            }
        /**得到20170713格式的系统日期 */
        val sysTime2: String
            get() {
                val sdf = SimpleDateFormat("yyyyMMdd")
                return sdf.format(java.util.Date())
            }

        /**得到一天的前一天，传入和返回格式都是20170713 */
        fun getBeforeDay(day: String): String {
            var date: java.util.Date = StringToDate2(day)
            val calendar = Calendar.getInstance()
            calendar.time = date
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            date = calendar.time
            val sdf = SimpleDateFormat("yyyyMMdd")
            return sdf.format(date)
        }

        /**得到[2017-03-07 星期二]类型的数据 */
        fun getDateStr(d: String): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd EEEE")
            val date = StringToDate2(d)
            return sdf.format(date)
        }

//        /**
//         * 取得当前时间的日期字符串
//         *
//         * @return
//         */
//        val dateNow: String
//            get() {
//                val cal = Calendar.getInstance()
//                var retStr = ""
//                retStr = (cal.get(cal.YEAR).toString() + "-" + (cal.get(cal.MONTH) + 1) + "-"
//                        + cal.get(cal.DAY_OF_MONTH))
//                return retStr
//            }

        val dateSerial: String
            get() {
                val sdf = SimpleDateFormat("yyyyMMddHHmmssSSSSSS")
                return sdf.format(java.util.Date())
            }

        val dateShortSerial: String
            get() {
                val sdf = SimpleDateFormat("yyMMddHHmmssSSSS")
                return sdf.format(java.util.Date())
            }

        val date: String
            get() {
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                return sdf.format(java.util.Date())
            }

        fun formatDateYMD(str: String): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            return sdf.format(StringToDate(str))
        }

        fun formatDate(date: java.util.Date?): String {
            if (date != null) {
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                return sdf.format(date)
            } else {
                return ""
            }
        }


        fun getDateTOString(format: String): String {
            val sdf = SimpleDateFormat(format)
            return sdf.format(java.util.Date())
        }

        fun getDateYear(ds: String): String {
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            var d = java.util.Date()
            try {
                d = sdf.parse(ds)
                calendar.time = d
            } catch (e: ParseException) {
                // TODO 自动生成 catch 块
                e.printStackTrace()
            }

            return calendar.get(Calendar.YEAR).toString()
        }

        fun getDateMonth(ds: String): String {
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            var d = java.util.Date()
            try {
                d = sdf.parse(ds)
                calendar.time = d
            } catch (e: ParseException) {
                // TODO 自动生成 catch 块
                e.printStackTrace()
            }

            return (calendar.get(Calendar.MONTH) + 1).toString()
        }

        fun getDateDay(ds: String): String {
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            var d = java.util.Date()
            try {
                d = sdf.parse(ds)
                calendar.time = d
            } catch (e: ParseException) {
                // TODO 自动生成 catch 块
                e.printStackTrace()
            }

            return calendar.get(Calendar.DAY_OF_MONTH).toString()
        }

        fun getDateHour(ds: String): String {
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyy-MM-dd HH")
            var d = java.util.Date()
            try {
                d = sdf.parse(ds)
                calendar.time = d
            } catch (e: ParseException) {
                // TODO 自动生成 catch 块
                e.printStackTrace()
            }

            return calendar.get(Calendar.HOUR_OF_DAY).toString()
        }

        fun formatDateTime(ds: String): String? {
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyyMMddHHmmss")
            val sdf2 = SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss")
            var d = java.util.Date()
            var df: String? = null
            try {
                d = sdf2.parse(ds)
                df = sdf.format(d)
            } catch (e: ParseException) {
                // TODO 自动生成 catch 块
                e.printStackTrace()
            }

            return df
        }

        fun formatDate(ds: String): String? {
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            var d = java.util.Date()
            var df: String? = null

            val sdf2 = SimpleDateFormat("yyyy-MM-dd")
            try {
                d = sdf2.parse(ds)
                df = sdf.format(d)
            } catch (e: ParseException) {
                // TODO 自动生成 catch 块
                e.printStackTrace()
            }

            return df
        }

        fun formatDate(): String? {
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val d = java.util.Date()
            var df: String? = null

            df = sdf.format(d)
            return df
        }

        fun formatDateYM(): String? {
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyyMM")
            val d = java.util.Date()
            var df: String? = null

            df = sdf.format(d)

            return df
        }

        fun formatLongTime(time: String): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val dt = Date(java.lang.Long.parseLong(time))
            return sdf.format(dt)
        }

        fun formatShortDateTime(ds: String): String? {
            val sdf = SimpleDateFormat("yyyyMMddHHmm")
            val sdf2 = SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss")
            var d = java.util.Date()
            var df: String? = null
            try {
                d = sdf2.parse(ds)
                df = sdf.format(d)
            } catch (e: ParseException) {
                // TODO 自动生成 catch 块
                e.printStackTrace()
            }

            return df
        }

        fun formatStringDateTime(ds: String): String? {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val sdf2 = SimpleDateFormat("yyyy-MM-dd")
            var d = java.util.Date()
            var df: String? = null
            try {
                d = sdf2.parse(ds)
                df = sdf.format(d)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return df
        }

        /**
         * 添加(或减小)时间
         *
         * @param date
         * @param field
         * 要添加(或减小)的字段(年或月或日或...)
         * @param amount
         * 要添加(或减小)的数量，amount为正数时，是添加，为负数时是减小
         * @return 日期
         */
        fun add(date: Date, field: Int, amount: Int): Date {
            val uDate = convert2JavaDate(date)
            val ca = Calendar.getInstance()
            ca.time = uDate
            ca.add(field, amount)
            val uNewDate = ca.time
            val newDate = convert2SqlDate(uNewDate)
            return convert2SqlDate(uNewDate)
        }

        /**
         * 添加(或减小)时间
         *
         * @param date
         * @param field
         * 要添加(或减小)的字段(年或月或日或...)
         * @param amount
         * 要添加(或减小)的数量，amount为正数时，是添加，为负数时是减小
         * @param pattern
         * 格式化模式
         * @return 格式化后的日期字符串
         */
        fun add(date: Date, field: Int, amount: Int, pattern: String): String {

            val uDate = convert2JavaDate(date)
            val ca = Calendar.getInstance()
            ca.time = uDate
            ca.add(field, amount)

            return format(ca.time, pattern)
        }

        /**
         * 添加(或减小)时间
         *
         * @param date
         * @param field
         * 要添加(或减小)的字段(年或月或日或...)
         * @param amount
         * 要添加(或减小)的数量，amount为正数时，是添加，为负数时是减小
         * @param pattern
         * 格式化模式
         * @return 格式化后的日期字符串
         * @throws ParseException
         */
        @Throws(ParseException::class)
        fun add(date: String, field: Int, amount: Int, pattern: String): String {
            val ca = Calendar.getInstance()
            ca.time = parse(date, pattern)
            ca.add(field, amount)

            return format(ca.time, pattern)
        }

        /**
         * 根据字符串生成日期
         *
         * @param dateStr
         * @param pattern
         * @return Date
         * @throws ParseException
         */

        @Throws(ParseException::class)
        fun parse(dateStr: String, pattern: String): java.util.Date {
            val format = SimpleDateFormat(pattern)
            return format.parse(dateStr)
        }

        /**
         * 格式化日期
         *
         * @param date
         * @param pattern
         * @return String
         */
        fun format(date: java.util.Date, pattern: String): String {
            val format = SimpleDateFormat(pattern)
            return format.format(date)
        }

        /**
         * 将java.util.Date转换为java.sql.Date
         *
         * @param javaDate
         * @return
         */
        fun convert2SqlDate(javaDate: java.util.Date): Date {
            val sd: Date

            sd = Date(javaDate.time)
            return sd

        }

        /**
         * 将java.sql.Date转换为java.util.Date
         *
         * @param sqlDate
         * @return
         */
        fun convert2JavaDate(sqlDate: Date): java.util.Date {
            return Date(sqlDate.time)
        }

        @JvmStatic
        fun main(args: Array<String>) {

            println(DateUtils.formatDate("2006-8-23"))
            println(DateUtils.formatShortDateTime("2006-08-23 20:12:30"))
            println(DateUtils.formatDateTime("2006-08-23 20:13:22"))

            val gs = "2002-01-01"

            println(DateUtils.formatDate())
            println(DateUtils.formatDateYM())

            println("revertList[0].kindValue".substring(0,
                    "revertList[0].kindValue".indexOf(".") + 1))

        }

        // 定义格式，不显示毫秒
        // 获取系统当前时间
        val sqliteDateTime: String
            get() {

                val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val now = Timestamp(System.currentTimeMillis())
                return df.format(now)

            }

        fun formatDateToCompare(date: java.util.Date): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return sdf.format(date)
        }

        /** 得到当前时间44天前的日期  */
        // 第2天，第x天，照加。如果是负数，表示前n天。
        val pre44Day: java.util.Date
            get() {
                val cal = Calendar.getInstance()
                cal.time = java.util.Date()
                cal.add(Calendar.DAY_OF_MONTH, -90)
                println("前面的日期:" + SimpleDateFormat("yyyy-MM-dd").format(cal.time))
                return cal.time
            }

        /** 得到当前时间45天后的日期  */
        // 第2天，第x天，照加。如果是负数，表示前n天。
        val next45Day: java.util.Date
            get() {
                val cal = Calendar.getInstance()
                cal.time = java.util.Date()
                cal.add(Calendar.DAY_OF_MONTH, 90)
                println("后面的日期:" + SimpleDateFormat("yyyy-MM-dd").format(cal.time))
                return cal.time
            }

        /** 判断该日期是否在当前日期的90天内  */
        fun isInDate(dt: java.util.Date): Boolean {

            println("当前的日期:" + SimpleDateFormat("yyyy-MM-dd").format(dt.time))
            return dt.compareTo(pre44Day) >= 0 && dt.compareTo(next45Day) <= 0
        }

        /** 判断该日期是否在当前日期的90天内  */
        //	public static boolean isInDate(DateVO dateVO) {
        //		java.util.Date dt = parseDateVO(dateVO);
        //
        //		if (dt == null) {
        //			return false;
        //		}
        //
        //		System.out.println("当前的日期:"
        //				+ new SimpleDateFormat("yyyy-MM-dd").format(dt.getTime()));
        //		return dt.compareTo(getPre44Day()) >= 0
        //				&& dt.compareTo(getNext45Day()) <= 0;
        //	}

        /**
         * @param DateStr1
         * @param DateStr2
         * @return 如果dateTime1大于dateTime2,则返回1; 如果dateTime1等于dateTime2,则返回0;
         * 如果dateTime1小于dateTime2,则返回-1;
         */
        fun compareToDate(DateStr1: java.util.Date, DateStr2: String): Int {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            var dateTime2: java.util.Date? = null
            try {
                dateTime2 = dateFormat.parse(DateStr2)
            } catch (e: ParseException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }

            return DateStr1.compareTo(dateTime2)
        }

        /**
         * 判断日期是否有效,包括闰年的情况
         *
         * @param date
         * YYYY-mm-dd
         * @return
         */
        fun isDate(date: String): Boolean {
            val reg = StringBuffer(
                    "^((\\d{2}(([02468][048])|([13579][26]))-?((((0?")
            reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))")
            reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|")
            reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12")
            reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))")
            reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))")
            reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[")
            reg.append("1-9])|(1[0-9])|(2[0-8]))))))")
            val p = Pattern.compile(reg.toString())
            return p.matcher(date).matches()
        }
    }

}
