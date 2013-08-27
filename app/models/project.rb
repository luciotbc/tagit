class Project < ActiveRecord::Base
  belongs_to :user
  attr_accessible :goal, :name, :video
  validate :user, :presence => true
  # Paperclip
  has_attached_file :video
 
end
