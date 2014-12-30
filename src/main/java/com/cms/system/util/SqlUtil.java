package com.cms.system.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class SqlUtil {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMddHH:mm:ss");
    private static SimpleDateFormat dtFormat = new SimpleDateFormat();

    private static DecimalFormat df = new DecimalFormat("#,##0");
    private static DecimalFormat df1 = new DecimalFormat();

    public static String getLimitRecord(String sql, int page, int pagesize) {
        if (page <= 1)
            page = 1;
        if (pagesize <= 0)
            pagesize = 0;
        int beginrow = (page - 1) * pagesize ;
        int endrow = beginrow + pagesize;        
        //sql = "SELECT * FROM (SELECT a.*, ROWNUM rn FROM ( "+sql + ") a WHERE ROWNUM < "+endrow+" ) WHERE rn >= "+beginrow;
        sql = "SELECT * FROM ( "+sql + ") a  limit  "+beginrow+" , "+endrow;
        return sql;
    }
    
    public static String getCountSql(String sql){
    	if (sql==null) return null;
    	String sql1 = sql.toLowerCase();
    	int n1 = sql1.indexOf("select ");
    	int n2 = sql1.indexOf(" from ");
    	if (n1<0||n2<0) return null;
    	
    	String sql2 = sql.substring(0, n1+7)+"count(*) "+sql.substring(n2);
    	
    	return sql2;
    	
    }

    public static String getFormatDate(long time) {
        return dateFormat.format(new Date(time));
    }

    public static String getFormatDateTime(long time) {
        return dateFormat1.format(new Date(time));
    }

    public static String getFormatDate(long time, String format) {
        synchronized (dtFormat) {
            dtFormat.applyPattern(format);
            return dtFormat.format(new Date(time));
        }
    }

    public static String getFormatDecimal(double db) {
        return df.format(db);
    }

    public static String getFormatDecimal(double db, String format) {
        synchronized (df1) {
            df1.applyPattern(format);
            return df1.format(db);
        }
    }

    public static String getString(Map map, String key) {
        return getString(map, key, true);
    }

    public static String getString(Map map, String key, boolean checkNull) {
        if (map == null) {
            if (checkNull) {
                return "";
            } else {
                return null;
            }
        }
        Object value = map.get(key);
        if (value == null) {
            value = map.get(key.toUpperCase());
        }
        if (value == null) {
            if (checkNull) {
                return "";
            } else {
                return null;
            }
        }

        String s = null;
        if (value instanceof String[]) {
            String[] s1 = (String[]) value;
            if (s1 != null && s1.length >= 1) {
                s = s1[0];
            }
        } else {
            s = (String) value;
        }

        if (checkNull) {
            if (s == null)
                s = "";
        }
        // if (s!=null&&s.indexOf("%")>=0){
        // s = AjaxUtils.unescape(s);
        // }
        return s;
    }

    public static double getDouble(Map map, String key) {
        if (map == null)
            return 0D;
        Object o = map.get(key);
        if (o == null) {
            return 0.0D;
        }
        if (o instanceof Double) {
            return ((Double) o).doubleValue();
        }
        if (o instanceof BigDecimal) {
            return ((BigDecimal) o).doubleValue();
        }
        return 0.0D;
    }

    public static int getInt(Map map, String key) {
        Object o = map.get(key);
        if (o == null) {
            return 0;
        }
        if (o instanceof Integer) {
            return ((Integer) o).intValue();
        }
        if (o instanceof BigDecimal) {
            return ((BigDecimal) o).intValue();
        }
        return 0;
    }

    public static int getRealInt(Map map, String key) {
        Integer db = (Integer) map.get(key);
        if (db == null)
            return 0;
        return db.intValue();
    }

    public static int getPageCount(int totalitem, int pagesize) {
        int totalpage = 0;
        if ((totalitem % pagesize) == 0) {
            totalpage = totalitem / pagesize;
        } else {
            totalpage = totalitem / pagesize + 1;
        }
        return totalpage;
    }

    public static Timestamp getTimestamp(Map map, String key) {
        return (Timestamp) map.get(key);
    }

    public static String[] getSplitString(String str, String split) {
        if (str == null | split == null)
            return null;
        StringTokenizer st = new StringTokenizer(str, split);
        List list = new ArrayList();
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }

        if (list.size() == 0)
            return null;

        String[] arr = new String[list.size()];

        return (String[]) list.toArray(arr);

    }


    public static Map cloneMap(Map m) {
        if (m == null)
            return null;
        Map map = new HashMap();
        Iterator it = m.keySet().iterator();
        Object key = null;
        while (it.hasNext()) {
            key = it.next();
            map.put(key, m.get(key));
        }
        return map;

    }

    /**
     * 从身份证中读取出生日期
     * 
     * @param idcard
     * @return
     */
    public static String getBirthdayFormIdcard(String idcard) {
        if (idcard == null)
            return null;
        if (idcard.length() != 15 && idcard.length() != 18)
            return null;
        try {
            String s = "";
            if (idcard.length() == 15) {
                s = "19" + idcard.substring(6, 12);
            } else {
                s = idcard.substring(6, 14);
            }
            return s;
        } catch (Exception ex) {
            return null;
        }

    }

    /**
     * 身份证的转换，由15位计算18位，由18位取15位
     * 参考直销的accoreq/ar_pubfunc.pc的char * TransIdentityNo(char * src)方法
     *
     * @param idCardNo String
     * @return String
     */
    public static String TransIdentityNo(String idCardNo) {
      idCardNo = trimNull(idCardNo);
      char[] src = idCardNo.toCharArray();
      String result = "";

      char[] pre = new char[6]; //前面6位
      char[] tail = new char[9]; //后面9位
      char[] rlt = new char[18]; //总的18位
      //code 为校验码
      char code = '\0';
      char codetmp;
      int i = 0, num = 0;
      if (src.length == 15) {

        System.arraycopy(src, 0, pre, 0, 6);
        System.arraycopy(src, 6, tail, 0, 9);

        System.arraycopy(pre, 0, rlt, 0, 6);
        System.arraycopy("19".toCharArray(), 0, rlt, 6, 2);
        System.arraycopy(tail, 0, rlt, 8, 9);

        for (i = 2; i < 19; i++) {
          codetmp = rlt[18 - i];
          if ( (codetmp >= 48) && (codetmp <= 57)) {
            num += ( (int) Math.pow(2, i - 1) % 11) * Integer.parseInt(String.valueOf(codetmp));
          }
          else
            return idCardNo;
        }
        num %= 11;

        switch (num) {
          case 0:
            code = '1';
            break;
          case 1:
            code = '0';
            break;
          case 2:
            code = 'X';
            break;
          default: {
            code =  Character.forDigit(12 - num, 10);
          }
        }
        rlt[17] = code;

        result = new String(rlt, 0, 18);

      }
      else if (src.length == 18) {

        result = idCardNo.substring(0, 6) + idCardNo.substring(8, 17);

      }
      else {
        return idCardNo;
      }
      return result;
    }

    /**
     * 去掉字符串前后的空格，如果参数为null，则返回空字符串
     *
     * @param str
     * @return
     */
    public static String trimNull(String str) {
      String result = "";
      if(str != null) {
        result = str.trim();
      }

      return result;
    }
}
