 function HashMap()
 {
     /** Map size **/
     var size = 0;
     /** 对象 **/
     var entry = new Object();
     
     /** Store **/
     this.put = function (key , value)
     {
         if(!this.containsKey(key))
         {
             size ++ ;
         }
         entry[key] = value;
     }
     
     /** Get **/
     this.get = function (key)
     {
         if( this.containsKey(key) )
         {
             return entry[key];
         }
         else
         {
             return null;
         }
     }
     
     /** Remove **/
     this.remove = function ( key )
     {
         if( delete entry[key] )
         {
             size --;
         }
     }
     
     /** Contain Key **/
     this.containsKey = function ( key )
     {
         return (key in entry);
     }
     
     /** Contain Value **/
     this.containsValue = function ( value )
     {
         for(var prop in entry)
         {
             if(entry[prop] == value)
             {
                 return true;
             }
         }
         return false;
     }
     
     /** All Value **/
     this.values = function ()
     {
         var values = new Array(size);
         for(var prop in entry)
         {
             values.push(entry[prop]);
         }
         return values;
     }
     
     /** All Key **/
     this.keys = function ()
     {
         var keys = new Array(size);
         for(var prop in entry)
         {
             keys.push(prop);
         }
         return keys;
     }
     
     /** Map Size **/
     this.size = function ()
     {
         return size;
     }
 }