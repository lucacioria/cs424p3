require 'rubygems'  
require 'active_record'  

ActiveRecord::Base.establish_connection(  
	:adapter => "mysql",  
	:host => "inacarcrash.cnrtm99w3c2x.us-east-1.rds.amazonaws.com",  
	:database => "crash",  
	:username => "organic",  
	:password => "sharpcheddar"
)  
  
class Crash < ActiveRecord::Base
end

puts Crash.count