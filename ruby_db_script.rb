require 'rubygems'  
require 'active_record'  

ActiveRecord::Base.establish_connection(  
	:adapter => "mysql",  
	:host => "inacarcrash.cnrtm99w3c2x.us-east-1.rds.amazonaws.com",  
	:database => "crash",  
	:username => "organic",  
	:password => "sharpcheddar"
)  
  
class Krash < ActiveRecord::Base
end

def do_save(to_save)
	puts "saving.."
	Krash.transaction do
		to_save.each{|x| x.save}
		print "."
	end
	to_save.clear
	puts "save.. #{to_save.count} is 0"
end

to_save = []

Krash.find_each {|x|
	puts x.id / 300000.0 if x.id % 100 == 0
	if x.alcohol_involved.nil? then
		x.alcohol_involved_C_max = -1
	elsif x.alcohol_involved.include? "1" then
		x.alcohol_involved_C_max = 1 
	elsif x.alcohol_involved.include? "0" then
		x.alcohol_involved_C_max = 0
	else	
		x.alcohol_involved_C_max = -1
	end
	to_save << x
	do_save(to_save) if x.id % 500 == 0
}